package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EJourneyStatus {
    NOT_COMPLETED("미완료"),
    BEFORE_START("진행 직전"),
    IN_PROGRESS("진행 중"),
    COMPLETED("진행 완료");

    private final String label;
}
