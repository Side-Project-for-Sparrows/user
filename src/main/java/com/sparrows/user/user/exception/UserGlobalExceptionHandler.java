package com.sparrows.user.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import static com.sparrows.user.user.exception.UserErrorCode.INTERNAL_SERVER;
import static com.sparrows.user.user.exception.UserErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice("com.sparrows.user.user")
public class UserGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        final UserErrorResponse errorResponse = UserErrorResponse.of(e);
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        final UserErrorResponse errorResponse = UserErrorResponse.of(INTERNAL_SERVER);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER.getStatus());
    }


    @ExceptionHandler({
            MissingServletRequestPartException.class,
            MissingRequestValueException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class
    })
    public ResponseEntity<UserErrorResponse> handleRequestException(Exception e) {
        log.error(e.getMessage(), e);
        final UserErrorResponse errorResponse = UserErrorResponse.of(INVALID_REQUEST);
        return new ResponseEntity<>(errorResponse, INVALID_REQUEST.getStatus());
    }

}

