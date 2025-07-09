package com.heartz.byeboo.adapter.in.web.dto.response;

import com.heartz.byeboo.adapter.in.web.dto.response.quest.AllQuestDetailResponseDto;
import com.heartz.byeboo.domain.type.EStep;

import java.util.List;

public record StepResponseDto(
        Integer stepNumber,
        String step,
        List<AllQuestDetailResponseDto> quests
) {
    public static StepResponseDto of(Integer stepNumber, EStep step, List<AllQuestDetailResponseDto> quests) {
        return new StepResponseDto(stepNumber, step.getLabel(), quests);
    }
}
