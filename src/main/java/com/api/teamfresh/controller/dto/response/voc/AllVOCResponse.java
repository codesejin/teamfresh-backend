package com.api.teamfresh.controller.dto.response.voc;

import com.api.teamfresh.controller.dto.response.CarrierResponse;
import com.api.teamfresh.controller.dto.response.CompensationResponse;
import com.api.teamfresh.controller.dto.response.CustomerResponse;
import com.api.teamfresh.controller.dto.response.PenaltyResponse;
import com.api.teamfresh.domain.constants.BlameType;
import com.api.teamfresh.domain.entity.Compensation;
import com.api.teamfresh.domain.entity.Penalty;
import com.api.teamfresh.domain.entity.VOC;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AllVOCResponse {
    private long id;
    private BlameType blameType; // 귀책 당사자
    private String content; // 귀책 내용
    private String claimEntryType; // 클레임 인입 경로
    private PenaltyResponse penalty; // 패널티 내용 + 이의 제기 여부 + 기사 확인 여부
    // 배상 정보
    private CompensationResponse compensation;
    // 운송사 정보
    private CarrierResponse carrier;
    // 고객사 정보
    private CustomerResponse customer;

    private AllVOCResponse(VOC voc) {
        this.id = voc.getId();
        this.blameType = voc.getBlameType();
        this.content = voc.getVocContent().getDescription();
        this.claimEntryType = voc.getClaimEntryType().getEntryTypeName();
        this.penalty = PenaltyResponse.of(voc.getPenalty());
        this.compensation = CompensationResponse.of(voc.getCompensation());
        this.carrier = CarrierResponse.createOnlyCarrierInfo(voc.getCarrier());
        this.customer = CustomerResponse.of(voc.getCustomer());
    }

    public static AllVOCResponse of(VOC voc) {
        return new AllVOCResponse(voc);
    }
}
