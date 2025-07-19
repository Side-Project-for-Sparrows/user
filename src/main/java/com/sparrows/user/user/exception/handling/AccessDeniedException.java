package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class AccessDeniedException extends BusinessException {

    public AccessDeniedException() {
        super(UserErrorCode.ACCESS_DENIED);
    }
}
