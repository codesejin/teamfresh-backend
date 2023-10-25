package com.api.teamfresh.domain.constants;

public enum VOCContent {
    WRONG_DELIVERY_LOCATION("잘못된 위치로 배송"),
    DELAYED_DELIVERY("배송 지연"),
    MISSING_ITEM("물건 누락"),
    DAMAGED_ITEM("물건 파손");

    private final String description;

    VOCContent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
