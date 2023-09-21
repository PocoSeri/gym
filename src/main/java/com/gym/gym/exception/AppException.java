package com.gym.gym.exception;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    protected final ErrCode errCode;
    protected final HttpStatus status;
    protected Object payload;
    private static final long serialVersionUID = -195778065791225557L;

    public AppException(ErrCode errCode, String message) {
        super(message);
        this.errCode = errCode;
        this.status = null;
    }

    public AppException(ErrCode errCode, String message, HttpStatus status) {
        super(message);
        this.errCode = errCode;
        this.status = status;
    }

    public AppException(ErrCode errCode) {
        this.errCode = errCode;
        this.status = null;
    }

    public ErrCode getErrCode() {
        return this.errCode;
    }

    public HttpStatus getHttpStatus() {
        if (this.status != null) {
            return this.status;
        } else {
            switch (this.getErrCode()) {
                case BAD_INPUT:
                case EVENT_INVALID:
                    return HttpStatus.BAD_REQUEST;
                case NOT_FOUND:
                case DATA_INTEGRITY:
                case ID_SHOULD_BE_EMPTY:
                case UNPROCESSABLE_ENTITY:
                case IMPLEMENTATION_MISSING:
                    return HttpStatus.UNPROCESSABLE_ENTITY;
                case ALREADY_EXISTS:
                    return HttpStatus.CONFLICT;
                default:
                    return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }

    public static void stateThat(boolean state, @NonNull ErrCode errCode, @NonNull String errorMessage) throws AppException {
        if (errCode == null) {
            throw new NullPointerException("errCode is marked non-null but is null");
        } else if (errorMessage == null) {
            throw new NullPointerException("errorMessage is marked non-null but is null");
        } else if (!state) {
            throw new AppException(errCode, errorMessage);
        }
    }

    public static void stateThat(boolean state, @NonNull ErrCode errCode, @NonNull String errorMessage, Object... errorMessageArgs) throws AppException {
        if (errCode == null) {
            throw new NullPointerException("errCode is marked non-null but is null");
        } else if (errorMessage == null) {
            throw new NullPointerException("errorMessage is marked non-null but is null");
        } else if (!state) {
            throw new AppException(errCode, String.format(errorMessage, errorMessageArgs));
        }
    }

    public static void stateThat(boolean state, @NonNull AppException appException) throws AppException {
        if (appException == null) {
            throw new NullPointerException("appException is marked non-null but is null");
        } else if (!state) {
            throw appException;
        }
    }

    public Object getPayload() {
        return this.payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public static enum ErrCode {
        BAD_INPUT,
        NOT_FOUND,
        EVENT_INVALID,
        DATA_INTEGRITY,
        ALREADY_EXISTS,
        ID_SHOULD_BE_EMPTY,
        UNPROCESSABLE_ENTITY,
        IMPLEMENTATION_MISSING,
        BROKEN_EVENT_PUBLISHER,
        QUERY_RESULT_TOO_LARGE;

        private ErrCode() {
        }
    }
}
