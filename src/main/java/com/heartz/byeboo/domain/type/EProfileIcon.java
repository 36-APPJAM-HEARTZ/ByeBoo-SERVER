package com.heartz.byeboo.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@RequiredArgsConstructor
public enum EProfileIcon {
    SADNESS("슬픔"),
    SELF_UNDERSTANDING("자기 이해"),
    SO_SO("그저 그런"),
    RELIEVED("후련함");

    private final String label;

    public static EProfileIcon getRandom() {
        EProfileIcon[] icons = values();
        int randomIndex = ThreadLocalRandom.current().nextInt(icons.length);
        return icons[randomIndex];
    }
}
