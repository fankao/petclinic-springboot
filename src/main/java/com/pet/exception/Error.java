package com.pet.exception;

import org.apache.logging.log4j.util.Strings;

import java.time.Instant;
import java.util.List;

public class Error {
    private String errorCode;
    private String message;
    private List<String> errors;
    private Integer status;
    private String url = "Not available";
    private String reqMethod = "Not available";
    private Instant timestamp;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public List<String> getErrors() {
        return errors;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Error setUrl(String url) {
        if (Strings.isNotBlank(url)) {
            this.url = url;
        }
        return this;
    }


    public Error setErrors(List<String> errors) {
        if (errors.size() > 0){
            this.errors = errors;
        }
        return this;
    }

    public Error setReqMethod(String method) {
        if (Strings.isNotBlank(method)) {
            this.reqMethod = method;
        }
        return this;
    }

    public Error setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
