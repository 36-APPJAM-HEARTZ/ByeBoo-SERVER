package com.heartz.byeboo.application.port.in.dto.response;

import com.heartz.byeboo.application.port.in.dto.response.quest.AllQuestDetailResponseDto;
import com.heartz.byeboo.domain.type.EStep;

import java.util.List;

public record StepResponseDto(
        Long stepNumber,
        String step,
        List<AllQuestDetailResponseDto> quests
) {
    public static StepResponseDto of(Long stepNumber, EStep step, List<AllQuestDetailResponseDto> quests) {
        return new StepResponseDto(stepNumber, step.getLabel(), quests);
    }
}
