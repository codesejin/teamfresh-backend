package com.api.teamfresh.domain.constants;

public enum ClaimEntryType {
    KAKAOTALK("카카오톡"),
    PHONE("전화"),
    NAVER_TALK("네이버 톡톡");

    private final String entryTypeName;

    ClaimEntryType(String entryTypeName) {
        this.entryTypeName = entryTypeName;
    }

    public String getEntryTypeName() {
        return entryTypeName;
    }
}
