package com.api.teamfresh.controller.api;

import com.api.teamfresh.service.CompensationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CompensationController {
    private final CompensationService compensationService;
    @PostMapping()
    public ResponseEntity<> createCompensation() {
//        compensationService.createCompensation()
    }
}
