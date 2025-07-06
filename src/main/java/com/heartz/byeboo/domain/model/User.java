package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EQuestStyle;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private Long id;
    private String name;
    private EQuestStyle questStyle;
    private Long currentNumber;

    public static User of(Long id, String name, EQuestStyle questStyle, Long currentNumber) {
        return User.builder()
                .id(id)
                .name(name)
                .questStyle(questStyle)
                .currentNumber(currentNumber)
                .build();
    }

    public void initializeCurrentNumber() {
        this.currentNumber = 0L;
    }
}
