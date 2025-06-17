package com.sparrows.user.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final UserErrorCode errorCode;

    public BusinessException(UserErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return errorCode.getStatus();
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
