package com.api.teamfresh.domain.constants;

/**
 * 클레임 상태 Enum
 */
public enum ClaimStatus {
    INCOMING("인입"),
    IN_PROGRESS("진행 중"),
    COMPLETED("완료");

    private String displayName;

    ClaimStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
