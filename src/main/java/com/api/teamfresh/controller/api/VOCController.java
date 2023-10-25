package com.api.teamfresh.controller.api;

import com.api.teamfresh.controller.dto.request.CreateVOCRequest;
import com.api.teamfresh.controller.dto.response.voc.AllVOCResponse;
import com.api.teamfresh.controller.dto.response.voc.CreateVOCResponse;
import com.api.teamfresh.service.VOCService;
import com.api.teamfresh.util.APIResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/voc")
@RestController
public class VOCController {

    private final VOCService vocService;

    // voc 전체 목록
    @GetMapping()
    public ResponseEntity<List<AllVOCResponse>> getAllVOC() {
        return ResponseEntity.status(HttpStatus.OK).body(vocService.getAllVOC());
    }

    // voc 등록
    @PostMapping()
    public ResponseEntity<CreateVOCResponse> createVOC(@RequestBody final CreateVOCRequest createVOCRequest) {
        // 모든 값이 들어오는지 유효성 검사
        createVOCRequest.verifyRequest(createVOCRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(vocService.createVOC(createVOCRequest));
    }

    // 고객사로부터 배상 요청 받은 경우
    @PatchMapping("/{vocId}/compensation")
    public ResponseEntity<APIResponse> updatedCompensationRequest(@PathVariable long vocId) {
        String result = vocService.updatedCompensationRequest(vocId);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.of(201,result));
    }
}

