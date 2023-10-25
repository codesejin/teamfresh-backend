package com.api.teamfresh.exception.util;

public enum PenaltyMessages {
    CONFIRMED("기사님이 승인하셨습니다."),
    OBJECTION("기사님이 이의제기 하셨습니다.");

    private final String message;

    PenaltyMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
