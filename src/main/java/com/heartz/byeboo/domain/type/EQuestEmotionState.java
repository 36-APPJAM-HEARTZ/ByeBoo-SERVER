package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EQuestEmotionState {
    NEUTRAL("그저 그런", "특별한 감정이 없었을 수도 있어요. 하지만 그런 날에도, 당신은 스스로를 이해하는 연습을 하고 있다는 걸 잊지 마세요."),
    SAD("슬픔", "마음이 조금 무거웠을지도 몰라요. 하지만 그 감정을 마주한 것만으로도, 이미 한 걸음 나아간 거예요."),
    RELIEVED("후련함", "마음이 가벼워졌다면 다행이에요. 당신은 지금 아주 건강하게 감정을 정리하고 있어요."),
    SELF_UNDERSTANDING("자기 이해", "퀘스트를 통해 스스로에 대해 더 잘 알게 되셨네요! 아주 바람직하게 나아가고 있어요.");

    private final String label;
    private final String description;
}
