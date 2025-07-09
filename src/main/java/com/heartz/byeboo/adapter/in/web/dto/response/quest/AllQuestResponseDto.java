package com.heartz.byeboo.adapter.in.web.dto.response.quest;

import com.heartz.byeboo.adapter.in.web.dto.response.StepResponseDto;

import java.util.List;

public record AllQuestResponseDto(
        String progressPeriod,
        Long currentStep,
        Boolean isCompleted,
        List<StepResponseDto> steps
) {
    public static AllQuestResponseDto of(
            String progressPeriod,
            Long currentStep,
            Boolean isCompleted,
            List<StepResponseDto> steps
    ) {
        return new AllQuestResponseDto(progressPeriod, currentStep, isCompleted, steps);
    }
}
