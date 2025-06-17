package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class UserRelationNotFoundException extends BusinessException {
    public UserRelationNotFoundException() {
        super(UserErrorCode.USER_RELATION_NOT_FOUND);
    }
}
