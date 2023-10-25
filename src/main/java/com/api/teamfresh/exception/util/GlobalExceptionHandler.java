package com.api.teamfresh.exception.util;

import com.api.teamfresh.exception.DriverNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(DriverNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
