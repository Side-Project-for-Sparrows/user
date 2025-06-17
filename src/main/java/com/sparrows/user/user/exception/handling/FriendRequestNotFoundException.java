package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class FriendRequestNotFoundException extends BusinessException {
    public FriendRequestNotFoundException() {
        super(UserErrorCode.FRIEND_REQUEST_NOT_FOUND);
    }
}
