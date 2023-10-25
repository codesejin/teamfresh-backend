package com.api.teamfresh.exception.util;

import com.api.teamfresh.exception.DriverNotFoundException;
import com.api.teamfresh.exception.PenaltyBadRequestException;
import com.api.teamfresh.exception.PenaltyNotFoundException;
import com.api.teamfresh.exception.VOCNotFoundException;
import com.api.teamfresh.util.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<APIResponse> handleNotFoundException(DriverNotFoundException ex) {
        return ResponseEntity.badRequest().body(APIResponse.of(400, ex.getMessage()));
    }

    @ExceptionHandler(VOCNotFoundException.class)
    public ResponseEntity<APIResponse> handleNotFoundException(VOCNotFoundException ex) {
        return ResponseEntity.badRequest().body(APIResponse.of(400, ex.getMessage()));
    }
    @ExceptionHandler(PenaltyNotFoundException.class)
    public ResponseEntity<APIResponse> handleNotFoundException(PenaltyNotFoundException ex) {
        return ResponseEntity.badRequest().body(APIResponse.of(400, ex.getMessage()));
    }

    @ExceptionHandler(PenaltyBadRequestException.class)
    public ResponseEntity<APIResponse> handleNotFoundException(PenaltyBadRequestException ex) {
        return ResponseEntity.badRequest().body(APIResponse.of(400, ex.getMessage()));
    }
}
