package com.pet.exception;

public class PetNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public PetNotFoundException(final String message) {
        super(ErrorCode.PET_NOT_FOUND.getErrMsgKey());
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
