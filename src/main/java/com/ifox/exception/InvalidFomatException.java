package com.ifox.exception;

import static com.ifox.exception.ErrorCode.INVALID_FORMAT;

import org.springframework.http.HttpStatus;

public class InvalidFomatException extends BaseException {

    protected InvalidFomatException(HttpStatus status) {
        super(status);
    }

    protected InvalidFomatException(HttpStatus status, ErrorCode errorCode, String errorMessage) {
        super(status, errorCode, errorMessage);
    }

    protected InvalidFomatException(HttpStatus status, ErrorCode errorCode) {
        super(status, errorCode);
    }

    public InvalidFomatException(String errorMessage) {
        super(HttpStatus.BAD_REQUEST, INVALID_FORMAT, errorMessage);
    }
}
