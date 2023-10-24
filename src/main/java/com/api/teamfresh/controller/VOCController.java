package com.api.teamfresh.controller;

import com.api.teamfresh.controller.dto.request.CreateVOC;
import com.api.teamfresh.controller.dto.response.CreateVOCResponse;
import com.api.teamfresh.domain.constants.BlameType;
import com.api.teamfresh.domain.entity.Claim;
import com.api.teamfresh.domain.entity.VOC;
import com.api.teamfresh.domain.repository.ClaimRepository;
import com.api.teamfresh.service.VOCService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class VOCController {
    
    private final ClaimRepository claimRepository;
    private final VOCService vocService;
    @GetMapping("/claims")
    public ResponseEntity<List<Claim>> getClaims() {
        List<Claim> claimList = claimRepository.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(claimList);
    }

    @PostMapping("/voc")
    public ResponseEntity<CreateVOCResponse> createVOC(@RequestBody final CreateVOC createVOC) {
        CreateVOCResponse voc = vocService.createVOC(createVOC);
        return ResponseEntity.status(HttpStatus.CREATED).body(voc);
    }
}
