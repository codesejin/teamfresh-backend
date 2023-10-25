package com.api.teamfresh.controller.api;

import com.api.teamfresh.service.CompensationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CompensationController {
    private final CompensationService compensationService;

    @PostMapping("/compensation/{vocId}")
    public ResponseEntity<String> createCompensation(@PathVariable long vocId,
                                                     @RequestParam long penaltyId) {
        String compensation = compensationService.createCompensation(vocId, penaltyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(compensation);
    }
}
