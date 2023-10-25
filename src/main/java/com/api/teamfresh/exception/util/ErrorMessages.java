package com.api.teamfresh.exception.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
    public static final String NOT_FOUND_VOC = "해당하는 VOC를 찾을 수 없습니다.";
    public static final String NOT_FOUND_DRIVER = "해당하는 운전기사를 찾을 수 없습니다.";
    public static final String NOT_FOUND_PENALTY = "해당하는 패널티를 찾을 수 없습니다.";
    public static final String CHECK_OBJECTION_WITH_PENALTY = "이의를 제기하려면 모두 적어주세요.";

}
