package com.api.teamfresh.exception.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
    public static final String NOT_FOUND_CLAIM = "해당하는 클레임 아이디를 찾을 수 없습니다.";
    public static final String NOT_FOUND_DRIVER = "해당하는 운전 기사를 찾을 수 없습니다.";

}
