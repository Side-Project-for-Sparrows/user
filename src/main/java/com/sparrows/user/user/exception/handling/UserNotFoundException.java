package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
