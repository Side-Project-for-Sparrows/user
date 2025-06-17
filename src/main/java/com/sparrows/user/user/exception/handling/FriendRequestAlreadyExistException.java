package com.sparrows.user.user.exception.handling;

import com.sparrows.user.user.exception.BusinessException;
import com.sparrows.user.user.exception.UserErrorCode;

public class FriendRequestAlreadyExistException extends BusinessException {
    public FriendRequestAlreadyExistException() {
        super(UserErrorCode.FRIEND_REQUEST_ALREADY_EXISTS);
    }
}
