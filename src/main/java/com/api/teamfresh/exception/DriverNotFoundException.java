package com.api.teamfresh.exception;

import org.springframework.http.HttpStatus;

public class DriverNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    public DriverNotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public DriverNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
