package com.api.teamfresh.exception.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
    public static final String NOT_FOUND_VOC = "해당하는 VOC를 찾을 수 없습니다.";
    public static final String NOT_FOUND_DRIVER = "해당하는 운전 기사를 찾을 수 없습니다.";

}
