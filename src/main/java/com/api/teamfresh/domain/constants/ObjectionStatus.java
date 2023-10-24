package com.api.teamfresh.domain.constants;

public enum ObjectionStatus {
    NO_OBJECTION("이의 제기 없음"),
    OBJECTION_RAISED("이의 제기됨");

    private final String description;

    ObjectionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ObjectionStatus getDefault() {
        return NO_OBJECTION;
    }
}