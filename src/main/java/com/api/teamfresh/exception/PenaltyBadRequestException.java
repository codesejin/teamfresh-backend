package com.api.teamfresh.exception;

import org.springframework.http.HttpStatus;

public class PenaltyBadRequestException extends RuntimeException {

    private final HttpStatus httpStatus;

    public PenaltyBadRequestException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public PenaltyBadRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
