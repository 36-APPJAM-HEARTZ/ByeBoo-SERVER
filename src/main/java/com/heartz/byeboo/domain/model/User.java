package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EPlatform;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.domain.type.ERole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private Long id;
    private String name;
    private EQuestStyle questStyle;
    private Long currentNumber;
    private String serialId;
    private EPlatform platform;
    private ERole role;

    public static User of(Long id, String name, EQuestStyle questStyle, Long currentNumber, EPlatform platform, ERole role, String serialId) {
        return User.builder()
                .id(id)
                .name(name)
                .questStyle(questStyle)
                .currentNumber(currentNumber)
                .role(role)
                .serialId(serialId)
                .platform(platform)
                .build();
    }

    public void initializeCurrentNumber() {
        this.currentNumber = 0L;
    }

    public void startNewJourney(){
        this.currentNumber = 1L;
    }

    public void updateCurrentNumber(){
        this.currentNumber++;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
