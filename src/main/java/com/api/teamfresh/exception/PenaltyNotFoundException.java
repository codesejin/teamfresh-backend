package com.api.teamfresh.exception;

import org.springframework.http.HttpStatus;

public class PenaltyNotFoundException  extends RuntimeException {

    private final HttpStatus httpStatus;

    public PenaltyNotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public PenaltyNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
