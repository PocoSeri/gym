package com.gym.gym.exception;

import com.gym.gym.base.model.restresponse.RestResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VirtualEntityApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(VirtualEntityApplicationExceptionHandler.class);

    public VirtualEntityApplicationExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleServerError(Exception ex, WebRequest request) {
        logger.error("Something has gone wrong in the server", ex);
        RestResponse<String> response = new RestResponse("KO", Collections.singletonMap("Message", "Something has gone wrong in the server"));
        return this.handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String objectName = error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(objectName, errorMessage);
        });
        logger.debug("Error validating input fields: {}", errors);
        RestResponse<String> response = new RestResponse("KO", errors);
        return this.handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String objectName = error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(objectName, errorMessage);
        });
        logger.debug("Error validating input fields: {}", errors);
        RestResponse<String> response = new RestResponse("KO", errors);
        return this.handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        logger.debug("Error validating parameter {}", ex.getMessage());
        RestResponse<String> response = new RestResponse("KO", Collections.singletonMap("Message", ex.getMessage()));
        return this.handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}