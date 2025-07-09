package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EStep {
    EXPRESS_EMOTION("감정 쏟아내기", 1),
    ORGANIZE_SITUATION("상황 정리하기", 2),
    REFLECT_ROLE("내 역할 돌아보기", 3),
    FIND_MEANING("관계의 의미 찾기", 4),
    UNDERSTAND_SELF("지금의 나 이해하기", 5),
    AWAKE_HEART("마음 깨우기", 1),
    LET_FLOW_EMOTION("감정 흘려보내기", 2),
    NAMING_EMOTION("감정에 이름 붙이기", 3),
    SMALL_ROUTINE("나를 위한 작은 루틴", 4),
    ;

    private final String label;
    private final Integer stepNumber;
}
