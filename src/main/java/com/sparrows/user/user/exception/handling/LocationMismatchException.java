package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class LocationMismatchException extends BusinessException {

    public LocationMismatchException() {
        super(UserErrorCode.LOCATION_MISMATCH);
    }
}
