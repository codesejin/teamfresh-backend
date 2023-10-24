package com.api.teamfresh.domain.constants;

public enum CarrierName {
    CJ_LOGISTICS("CJ 대한통운"),
    LOTTE_LOGISTICS("롯데로지스틱스"),
    HYUNDAI_LOGISTICS("현대택배"),
    KOREA_POST("우체국택배"),
    DHL("DHL");

    private final String displayName;

    CarrierName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
