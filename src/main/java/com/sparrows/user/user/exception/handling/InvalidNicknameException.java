package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class InvalidNicknameException extends BusinessException {

    public InvalidNicknameException() {
        super(UserErrorCode.INVALID_NICKNAME);
    }
}
