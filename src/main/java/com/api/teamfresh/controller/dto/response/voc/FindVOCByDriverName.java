package com.api.teamfresh.controller.dto.response.voc;

import com.api.teamfresh.domain.constants.BlameType;
import com.api.teamfresh.domain.constants.ClaimEntryType;
import com.api.teamfresh.domain.constants.ClaimStatus;
import com.api.teamfresh.domain.constants.VOCContent;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FindVOCByDriverName {
    private Long vocId;
    private Long carrierId;
    private BlameType blameType;
    private ClaimEntryType claimEntryType;
    private ClaimStatus claimStatus;
    private VOCContent vocContent;
    private String driverName;
    private String driverPhoneNumber;
    private LocalDateTime created_At;
    private LocalDateTime updated_at;
}
