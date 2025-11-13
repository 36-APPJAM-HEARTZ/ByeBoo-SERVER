package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ECharacterTapDialogue {
    BEFORE_START("저는 %s님을 도와드릴 보리예요."),
    START("앗! 저를 부르셨나요?"),
    IN_PROGRESS("저는 항상 %s님을 응원하고 있어요!"),
    COMPLETED("힘들 땐 언제나 저를 찾아주세요.");

    private final String TapDialogue;

    public String getTapDialogue(String userName) {
        if (this == BEFORE_START) {
            return String.format(TapDialogue, userName);
        }
        if (this == IN_PROGRESS) {
            return String.format(TapDialogue, userName);
        }
        return TapDialogue;
    }
}
