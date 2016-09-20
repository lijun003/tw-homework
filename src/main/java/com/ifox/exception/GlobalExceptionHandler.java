package com.ifox.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleException(BaseException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex), ex.getStatus());
    }
}
