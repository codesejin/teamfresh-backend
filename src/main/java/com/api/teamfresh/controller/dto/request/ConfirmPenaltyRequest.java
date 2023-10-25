package com.api.teamfresh.controller.dto.request;

import static com.api.teamfresh.exception.util.ErrorMessages.CHECK_OBJECTION_WITH_PENALTY;

import com.api.teamfresh.domain.constants.ObjectionStatus;
import com.api.teamfresh.exception.PenaltyBadRequestException;
import lombok.Getter;

@Getter
public class ConfirmPenaltyRequest {
    private String name;
    private String phoneNumber;
    private boolean confirmed; // true면 승인, false면 이의제기
    private ObjectionStatus objectionStatus;
    private String content;

    public boolean verifyConfirmed() {
        if (!isConfirmed()) {
            if (objectionStatus == null || content == null) {
                throw new PenaltyBadRequestException(CHECK_OBJECTION_WITH_PENALTY);
            }
        }
        return true;
    }
}
