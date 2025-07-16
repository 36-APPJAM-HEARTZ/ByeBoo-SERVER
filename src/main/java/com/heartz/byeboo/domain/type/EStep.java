package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EStep {
    EXPRESS_EMOTION("감정 쏟아내기", 1L),
    ORGANIZE_SITUATION("상황 정리하기", 2L),
    REFLECT_ROLE("내 역할 돌아보기", 3L),
    FIND_MEANING("관계의 의미 찾기", 4L),
    UNDERSTAND_SELF("지금의 나 이해하기", 5L),
    AWAKE_HEART("마음 깨우기", 1L),
    LET_FLOW_EMOTION("감정 흘려보내기", 2L),
    NAMING_EMOTION("감정에 이름 붙이기", 3L),
    SMALL_ROUTINE("나를 위한 루틴 만들기", 4L),
    ;

    private final String label;
    private final Long stepNumber;
}
