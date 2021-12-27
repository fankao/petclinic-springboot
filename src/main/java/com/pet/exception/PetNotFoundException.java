package com.pet.exception;

public class PetNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public PetNotFoundException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public PetNotFoundException(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.PET_NOT_FOUND.getErrMsgKey();
        this.errorCode = ErrorCode.PET_NOT_FOUND.getErrCode();
    }

    public String getErrMsgKey() {
        return errMsgKey;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
