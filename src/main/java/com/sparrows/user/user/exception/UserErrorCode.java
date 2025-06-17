package com.sparrows.user.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode {
    // Global
    INVALID_REQUEST(HttpStatus.UNPROCESSABLE_ENTITY, "INVALID REQUEST ERROR"),
    INTERNAL_SERVER(HttpStatus.UNPROCESSABLE_ENTITY, "INTERNAL SERVER ERROR"),

    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "User already exists"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),
    INVALID_NICKNAME(HttpStatus.BAD_REQUEST, "Invalid nickname"),
    NICKNAME_ALREADY_EXIST(HttpStatus.CONFLICT, "Nickname already taken"),
    LOCATION_MISMATCH(HttpStatus.BAD_REQUEST, "Location does not match"),

    FRIEND_REQUEST_ALREADY_EXISTS(HttpStatus.CONFLICT, "Duplicate or already accepted friend request."),
    FRIEND_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "No friend request found."),
    USER_RELATION_NOT_FOUND(HttpStatus.NOT_FOUND, "User relation does not exist"),
    USER_RELATION_ALREADY_EXIST(HttpStatus.CONFLICT, "User relation already exists");

    private final HttpStatus status;
    private final String message;
}
