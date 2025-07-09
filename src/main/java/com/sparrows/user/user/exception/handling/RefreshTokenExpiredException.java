package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class RefreshTokenExpiredException extends BusinessException {
    public RefreshTokenExpiredException() {
        super(UserErrorCode.REFRESH_TOKEN_EXPIRED);
    }
}
