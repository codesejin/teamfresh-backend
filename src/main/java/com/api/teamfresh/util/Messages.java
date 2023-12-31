package com.api.teamfresh.util;

public enum Messages {
    CONFIRMED("기사님이 승인하셨습니다."),
    OBJECTION("기사님이 이의제기 하셨습니다."),
    COMPENSATION_REGISTER("배상정보가 등록되었습니다."),

    COMPENSATION_REQUESTED("고객사가 배상 요청을 했습니다."),
    COMPENSATION_NOT_REQUESTED("고객사가 배상 요청을 하지 않았습니다.");
    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
