package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class UserRelationAlreadyExistException extends BusinessException {

    public UserRelationAlreadyExistException() {
        super(UserErrorCode.USER_RELATION_ALREADY_EXIST);
    }
}
