package com.api.teamfresh.exception;

import org.springframework.http.HttpStatus;

public class ClaimNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ClaimNotFoundException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public ClaimNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
