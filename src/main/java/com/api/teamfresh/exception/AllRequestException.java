package com.api.teamfresh.exception;

import org.springframework.http.HttpStatus;

public class AllRequestException  extends RuntimeException {

    private final HttpStatus httpStatus;

    public AllRequestException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public AllRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
