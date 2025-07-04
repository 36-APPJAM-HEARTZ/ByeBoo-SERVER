package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EStep {
    EXPRESS_EMOTION("감정 쏟아내기"),
    ORGANIZE_SITUATION("상황 정리하기"),
    REFLECT_ROLE("내 역할 돌아보기"),
    FIND_MEANING("관계의 의미 찾기"),
    UNDERSTAND_SELF("지금의 나 이해하기");

    private final String label;
}
