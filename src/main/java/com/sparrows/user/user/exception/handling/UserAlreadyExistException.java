package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class UserAlreadyExistException extends BusinessException {

    public UserAlreadyExistException() {
        super(UserErrorCode.USER_ALREADY_EXIST);
    }
}
