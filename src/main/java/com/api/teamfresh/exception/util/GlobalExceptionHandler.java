package com.api.teamfresh.exception.util;

import com.api.teamfresh.exception.ClaimNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClaimNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ClaimNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
