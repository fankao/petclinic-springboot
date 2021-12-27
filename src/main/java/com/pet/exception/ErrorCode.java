package com.pet.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    GENERIC_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "The system is unable to complete the request. Contact system support."),
    HTTP_MEDIATYPE_NOT_SUPPORTED(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Requested media type is not supported. Please use application/json or application/xml as 'Content-Type' header value"),
    HTTP_MESSAGE_NOT_WRITABLE(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Missing 'Accept' header. Please add 'Accept' header."),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE(HttpStatus.PRECONDITION_FAILED.toString(), "Requested 'Accept' header value is not supported. Please use application/json or application/xml as 'Accept' value"),
    JSON_PARSE_ERROR(HttpStatus.PRECONDITION_FAILED.toString(), "Make sure request payload should be a valid JSON object."),
    HTTP_MESSAGE_NOT_READABLE(HttpStatus.PRECONDITION_FAILED.toString(), "Make sure request payload should be a valid JSON or XML object according to 'Content-Type'."),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(HttpStatus.BAD_REQUEST.toString(), "Request method not supported."),
    CONSTRAINT_VIOLATION(HttpStatus.PRECONDITION_FAILED.toString(), "Validation failed."),
    ILLEGAL_ARGUMENT_EXCEPTION(HttpStatus.PRECONDITION_FAILED.toString(), "Invalid data passed."),
    RESOURCE_NOT_FOUND(HttpStatus.PRECONDITION_FAILED.toString(), "Requested resource not found"),
    OWNER_NOT_FOUND(HttpStatus.PRECONDITION_FAILED.toString(), "Requested owner not found"),
    PET_NOT_FOUND(HttpStatus.PRECONDITION_FAILED.toString(), "Requested pet not found"),
    ENTITY_NOT_FOUND(HttpStatus.PRECONDITION_FAILED.toString(), "Requested entity not found"),
    GENERIC_ALREADY_EXISTS(HttpStatus.PRECONDITION_FAILED.toString(), "Already exists.");

    private String errCode;
    private String errMsgKey;

    private ErrorCode(String errCode, String errMsgKey) {
        this.errCode = errCode;
        this.errMsgKey = errMsgKey;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsgKey() {
        return errMsgKey;
    }
}
