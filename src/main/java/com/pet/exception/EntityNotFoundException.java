package com.pet.exception;

public class EntityNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public EntityNotFoundException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public EntityNotFoundException(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.ENTITY_NOT_FOUND.getErrMsgKey();
        this.errorCode = ErrorCode.ENTITY_NOT_FOUND.getErrCode();
    }

    public String getErrMsgKey() {
        return errMsgKey;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
