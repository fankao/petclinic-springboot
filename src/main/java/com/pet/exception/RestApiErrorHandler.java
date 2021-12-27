package com.pet.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
@Slf4j
public class RestApiErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        Error error = ErrorUtils
                .createError(ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getErrMsgKey(),
                        ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getErrCode(),
                        HttpStatus.PRECONDITION_FAILED.value())
                .setUrl(request.getRequestURI().toString())
                .setReqMethod(request.getMethod())
                .setTimestamp(Instant.now())
                .setErrors(errors);
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(error);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(HttpServletRequest request,
                                                                          HttpMediaTypeNotSupportedException ex,
                                                                          Locale locale) {
        Error error = ErrorUtils
                .createError(ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrMsgKey(),
                        ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrCode(),
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTimestamp(Instant.now());
        log.info("HttpMediaTypeNotSupportedException :: request.getMethod(): " + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(HttpServletRequest request,
                                                                 EntityNotFoundException ex, Locale locale) {
        Error error = ErrorUtils
                .createError(
                        String.format("%s %s", ErrorCode.RESOURCE_NOT_FOUND.getErrMsgKey(), ex.getMessage()),
                        ex.getErrorCode(),
                        HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTimestamp(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<Error> handleCustomerNotFoundException(HttpServletRequest request,
                                                                 OwnerNotFoundException ex, Locale locale) {
        Error error = ErrorUtils
                .createError(
                        String.format("%s %s", ErrorCode.OWNER_NOT_FOUND.getErrMsgKey(), ex.getMessage()),
                        ex.getErrorCode(),
                        HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTimestamp(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<Error> handlePetNotFoundException(HttpServletRequest request,
                                                            PetNotFoundException ex, Locale locale) {
        Error error = ErrorUtils
                .createError(
                        String.format("%s %s", ErrorCode.PET_NOT_FOUND.getErrMsgKey(), ex.getMessage()),
                        ex.getErrorCode(),
                        HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTimestamp(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
