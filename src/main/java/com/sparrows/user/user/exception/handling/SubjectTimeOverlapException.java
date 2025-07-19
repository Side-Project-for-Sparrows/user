package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class SubjectTimeOverlapException extends BusinessException {
    public SubjectTimeOverlapException() {
        super(UserErrorCode.INVALID_SUBJECT);
    }
}
