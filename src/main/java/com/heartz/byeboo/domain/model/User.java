package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EQuestStyle;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String name;
    private EQuestStyle questStyle;
    private Long currentNumber;
}
