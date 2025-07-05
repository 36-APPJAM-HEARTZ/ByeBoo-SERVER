package com.heartz.byeboo.adapter.in.web.dto;

import com.heartz.byeboo.domain.type.EQuestStyle;

public record UserCreateRequestDto(
        String name,
        EQuestStyle questStyle
) {
}
