package com.api.teamfresh.controller.api;

import com.api.teamfresh.controller.dto.request.CreateVOC;
import com.api.teamfresh.controller.dto.response.voc.AllVOCResponse;
import com.api.teamfresh.controller.dto.response.voc.CreateVOCResponse;
import com.api.teamfresh.service.VOCService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class VOCController {

    private final VOCService vocService;

    // voc 전체 목록
    @GetMapping("/voc")
    public ResponseEntity<List<AllVOCResponse>> getAllVOC() {
        return ResponseEntity.status(HttpStatus.OK).body(vocService.getAllVOC());
    }

    // voc 등록
    @PostMapping("/voc")
    public ResponseEntity<CreateVOCResponse> createVOC(@RequestBody final CreateVOC createVOC) {
        CreateVOCResponse voc = vocService.createVOC(createVOC);
        return ResponseEntity.status(HttpStatus.CREATED).body(voc);
    }

}

