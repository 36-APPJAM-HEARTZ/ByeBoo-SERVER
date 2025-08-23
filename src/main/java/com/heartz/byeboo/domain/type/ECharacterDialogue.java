package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ECharacterDialogue {
    BEFORE_START("%s님의 이별 극복을 도와드릴게요"),
    START("%s님만의 속도로 나아가 봐요"),
    IN_PROGRESS("오늘도 잘 이겨내셨어요!"),
    COMPLETED("저는 언제나 여기 있어요!");

    private final String dialogue;

    public String getDialogue(String userName) {
        if (this == BEFORE_START) {
            return String.format(dialogue, userName);
        }
        if (this == START) {
            return String.format(dialogue, userName);
        }
        return dialogue;
    }
}
