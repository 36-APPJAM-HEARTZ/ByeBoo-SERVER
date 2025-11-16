package com.heartz.byeboo.adapter.in.web.dto.request;

import java.util.UUID;

public record ActiveQuestUpdateRequestDto(
        String answer,
        UUID imageKey
) {
}
