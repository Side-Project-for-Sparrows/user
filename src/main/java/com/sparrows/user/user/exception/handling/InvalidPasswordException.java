package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class InvalidPasswordException extends BusinessException {

    public InvalidPasswordException() {
        super(UserErrorCode.INVALID_PASSWORD);
    }
}
