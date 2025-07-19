package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class SubjectNotFoundException extends BusinessException {

    public SubjectNotFoundException() {
        super(UserErrorCode.SUBJECT_NOT_FOUND);
    }
}
