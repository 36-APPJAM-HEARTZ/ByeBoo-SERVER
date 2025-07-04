package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EQuestStyle {
    RECORDING("혼자서 감정을 기록하는 것을 좋아함"),
    ACTIVE("사람을 만나서 행동으로 해소하는 것을 좋아함");

    private final String label;
}
