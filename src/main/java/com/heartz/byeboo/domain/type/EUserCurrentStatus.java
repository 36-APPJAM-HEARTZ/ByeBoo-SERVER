package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EUserCurrentStatus {
    TODAY_NOT_COMPLETED_STATUS("오늘의 퀘스트 완료 전"),
    TODAY_COMPLETED_STATUS("오늘의 퀘스트 완료 후"),
    JOURNEY_COMPLETED_STATUS("여정 완료 후 & 새로운 여정 시작 전");

    private final String label;
}
