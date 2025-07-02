package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EQuestEmotionState {
    NEUTRAL("그저 그런"),
    SAD("슬픈"),
    RELIEVED("후련함"),
    SELF_UNDERSTANDING("자기 이해");

    private final String label;
}
