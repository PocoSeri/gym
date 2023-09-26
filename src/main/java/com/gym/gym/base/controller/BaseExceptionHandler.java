package com.gym.gym.base.controller;

import com.gym.gym.base.model.restresponse.RestResponse;
import com.gym.gym.exception.AppException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger PLATFORM_LOGGER = LoggerFactory.getLogger(BaseExceptionHandler.class);

    public BaseExceptionHandler() {
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new RestResponse<>(ex, "Invalid request"), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = FieldError.class.isAssignableFrom(error.getClass()) ? ((FieldError)error).getField() : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        RestResponse<String> restResponse = new RestResponse<>(errors, "Parameters sent by client are invalid");
        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HashMap<String, String> errors = new HashMap<>();
        String var10000 = ex.getPropertyName();
        String error = var10000 + " should be of type " + ((Class) Objects.requireNonNull(ex.getRequiredType())).getName();
        errors.put(ex.getClass().getSimpleName(), error);
        RestResponse<String> restResponse = new RestResponse<>(errors, "Parameters sent by client are invalid");
        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError)error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            } else {
                PLATFORM_LOGGER.warn("Bind Exception encountered but not added to HTTP response as it's not a field error");
                PLATFORM_LOGGER.warn(error.toString());
            }

        });
        RestResponse<String> restResponse = new RestResponse<>(errors, "Server could not bind request to required parameters");
        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AppException.class})
    public ResponseEntity<Object> handleGenericException(AppException ex) {
        return new ResponseEntity<>(new RestResponse<>(ex, ex.getPayload() != null ? ex.getPayload() : ""), ex.getHttpStatus());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    public static ResponseEntity<Object> notFound(EntityNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new RestResponse<>(ex, ""), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        this.logger.warn("Returning HTTP 500 Bad Request", ex);
        HashMap<String, String> errors = new HashMap<>();
        errors.put(ex.getClass().getSimpleName(), ex.getMessage());
        PLATFORM_LOGGER.error("Exception", ex);
        RestResponse<String> restResponse = new RestResponse<>(errors, "Internal server error. Please report issue");
        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodNotAllowedException.class})
    @ResponseBody
    public ResponseEntity<Object> handleMethodNotAllowed(MethodNotAllowedException ex) {
        this.logger.warn("Returning HTTP 405 Method not Allowed", ex);
        RestResponse<String> restResponse = new RestResponse<>(new HashMap<>(), ex.getMessage());
        return new ResponseEntity<>(restResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({ObjectRetrievalFailureException.class})
    public ResponseEntity<Object> handleObjectRetrievalFailureException(ObjectRetrievalFailureException ex) {
        return new ResponseEntity<>(new RestResponse<>(ex, "One or more related entities could not be retrieved"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ObjectOptimisticLockingFailureException.class})
    public ResponseEntity<Object> handleObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("version", "was null or doesn't match with the current version anymore as it might have been updated");
        return new ResponseEntity<>(new RestResponse<>(errors, "Entity version mismatch, please retrieve latest version before update"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        HashMap<String, String> errors = new HashMap();
        String outputMessage = "Parameters sent by client are invalid.";
        ex.getConstraintViolations().forEach((error) -> {
            String fieldName = error.getPropertyPath().toString();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new RestResponse<>(errors, outputMessage), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new RestResponse<>(Map.of(ex.getParameterName(), ex.getMessage()), "Missing required parameters."), status);
    }

    @ExceptionHandler({ResponseStatusException.class})
    @ResponseBody
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException responseStatusException) {
        HashMap<String, String> errors = new HashMap();
        RestResponse<String> restResponse = new RestResponse(errors, StringUtils.isNotBlank(responseStatusException.getReason()) ? responseStatusException.getReason() : responseStatusException.getMessage());
        return new ResponseEntity<>(restResponse, responseStatusException.getStatusCode());
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        String[] denylist = new String[]{"class.*", "Class.*", "*.class.*", "*.Class.*"};
        dataBinder.setDisallowedFields(denylist);
        this.logger.info("InitBinder blackList set");
    }
}
