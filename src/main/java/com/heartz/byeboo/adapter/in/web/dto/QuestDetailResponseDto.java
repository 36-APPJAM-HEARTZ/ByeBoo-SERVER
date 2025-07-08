package com.heartz.byeboo.adapter.in.web.dto;

import java.time.LocalDateTime;

public record QuestDetailResponseDto(
        Long stepNumber,
        Long questNumber,
        LocalDateTime createdAt,
        String question,
        String answer,
        String questEmotionState,
        String imageUrl
) {
}