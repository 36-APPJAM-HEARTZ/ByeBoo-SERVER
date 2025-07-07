package com.heartz.byeboo.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record RecordingQuestRequestDto(
        @NotBlank(message = "답변은 비어있을 수 없습니다.")
        String answer,
        @NotBlank(message = "감정은 비어있을 수 없습니다.")
        String questEmotionState
) {
}
