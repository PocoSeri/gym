package com.gym.gym.base.model.restresponse;

import com.gym.gym.exception.AppException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, String> errorMessages;
    private T output;

    public RestResponse() {
        this.errorMessages = new HashMap();
    }

    public RestResponse(T output) {
        this();
        this.output = output;
    }

    public RestResponse(Exception exception, T output) {
        this();
        this.addToErrorList(exception.getClass().getSimpleName(), exception.getMessage());
        this.output = output;
    }

    public RestResponse(AppException appException, T output) {
        this();
        this.addToErrorList(appException.getErrCode().toString(), appException.getMessage());
        this.output = output;
    }

    public RestResponse(Map<String, String> errorMessages, T output) {
        this();
        this.errorMessages = errorMessages;
        this.output = output;
    }

    public RestResponse(T output, Map<String, String> errorMessages) {
        this();
        this.errorMessages = errorMessages;
        this.output = output;
    }

    public Map<String, String> getErrorMessages() {
        return this.errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public T getOutput() {
        return this.output;
    }

    public void setOutput(T output) {
        this.output = output;
    }

    public String toString() {
        return "RestResponse [errorMessages=" + this.errorMessages + ", output=" + this.output + "]";
    }

    public void setValidationErrorList(Map<String, List<String>> validationErrorMap) {
        validationErrorMap.forEach((key, errorList) -> {
            this.addToErrorList(key, String.join(",", errorList));
        });
    }

    public void addToErrorList(String property, String message) {
        String messageListString = (String)this.errorMessages.getOrDefault(property, "");
        messageListString = messageListString + (messageListString.equals("") ? message : ", " + message);
        this.errorMessages.put(property, messageListString);
    }
}
