package com.api.teamfresh.controller;

import com.api.teamfresh.controller.dto.request.CreatePenaltyRequest;
import com.api.teamfresh.controller.dto.response.CreatePenaltyResponse;
import com.api.teamfresh.controller.dto.response.voc.CreateVOCResponse;
import com.api.teamfresh.service.PenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class PenaltyController {
    private final PenaltyService penaltyService;

    // 패널티 등록
    @PostMapping("/penalty")
    public ResponseEntity<CreatePenaltyResponse> createPenalty(@RequestBody final CreatePenaltyRequest createPenaltyRequest) {
        CreatePenaltyResponse penalty = penaltyService.createPenalty(createPenaltyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(penalty);
    }
}
