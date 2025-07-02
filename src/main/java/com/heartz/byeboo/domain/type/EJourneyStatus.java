package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EJourneyStatus {
    BEFORE("진행 전"),
    IN_PROGRESS("진행 중"),
    COMPLETED("진행 완료");

    private final String label;
}
