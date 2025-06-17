package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class NicknameAlreadyExistException extends BusinessException {

    public NicknameAlreadyExistException() {
        super(UserErrorCode.NICKNAME_ALREADY_EXIST);
    }
}
