package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EFeeling {
    EXHAUSTED("너무 힘들어요"),
    RECOVERING("극복 중이에요"),
    OVERCOMING("꽤 극복했어요");

    private final String label;
}
