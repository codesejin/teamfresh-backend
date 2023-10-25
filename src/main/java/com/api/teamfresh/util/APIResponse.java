package com.api.teamfresh.util;

import lombok.Getter;

@Getter
public class APIResponse {
    private int status;
    private String message;

    private APIResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public static APIResponse of (int status, String message) {
        return new APIResponse(status, message);
    }

}
