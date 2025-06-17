package com.sparrows.user.user.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserErrorResponse {

    private final int status;
    private final String message;

    private UserErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static UserErrorResponse of(BusinessException e) {
        return UserErrorResponse.builder()
                .status(e.getStatus().value())
                .message(e.getMessage())
                .build();
    }

    public static UserErrorResponse of(UserErrorCode e) {
        return UserErrorResponse.builder()
                .status(e.getStatus().value())
                .message(e.getMessage())
                .build();
    }
}

