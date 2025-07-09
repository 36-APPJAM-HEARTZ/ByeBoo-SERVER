package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ECharacterDialogue {
    BEFORE_START("제가 %s님의 이별 극복을 도와드릴게요."),
    START("천천히, 하지만 분명하게. 오늘도 나아가봐요."),
    IN_PROGRESS("괜찮아질 거예요. 오늘도 여기까지 잘 왔으니까요."),
    COMPLETED("저는 언제나 여기 있어요 :)");

    private final String dialogue;

    public String getDialogue(String userName) {
        if (this == BEFORE_START) {
            return String.format(dialogue, userName);
        }
        return dialogue;
    }
}
