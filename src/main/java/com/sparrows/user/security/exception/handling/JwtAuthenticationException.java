package com.sparrows.user.security.exception.handling;

import com.sparrows.user.security.exception.BusinessException;
import com.sparrows.user.security.exception.SecurityErrorCode;

public class JwtAuthenticationException extends BusinessException {

    public JwtAuthenticationException(SecurityErrorCode errorCode) {
        super(errorCode);
    }

}
