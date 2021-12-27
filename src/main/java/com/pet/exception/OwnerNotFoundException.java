package com.pet.exception;

public class OwnerNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public OwnerNotFoundException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public OwnerNotFoundException(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.OWNER_NOT_FOUND.getErrMsgKey();
        this.errorCode = ErrorCode.OWNER_NOT_FOUND.getErrCode();
    }

    public String getErrMsgKey() {
        return errMsgKey;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
