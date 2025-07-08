package com.heartz.byeboo.adapter.in.web.dto.request;

import java.util.UUID;

public record ActiveQuestRequestDto(
        String answer,
        String questEmotionState,
        UUID imageKey
) {
}
