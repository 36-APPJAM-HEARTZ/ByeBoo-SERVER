package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EQuestStyle {
    RECORDING("질문과 미션을 통해 나만의 삶을 회복해 나가요"),
    @Deprecated
    ACTIVE("사람을 만나서 행동으로 해소하는 것을 좋아함"),
    REUNION("X와의 재회를 위해 나를 먼저 돌아보고 상대를 이해해요");

    private final String label;
}
