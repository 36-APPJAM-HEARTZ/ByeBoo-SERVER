package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import java.time.LocalDate;

public record MyCommonQuestResponseDto(
        Long answerId,
        LocalDate writtenAt,
        String content,
        String question
) {
}
