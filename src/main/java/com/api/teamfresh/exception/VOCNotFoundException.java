package com.api.teamfresh.exception;

import org.springframework.http.HttpStatus;

public class VOCNotFoundException  extends RuntimeException {

    private final HttpStatus httpStatus;

    public VOCNotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public VOCNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
