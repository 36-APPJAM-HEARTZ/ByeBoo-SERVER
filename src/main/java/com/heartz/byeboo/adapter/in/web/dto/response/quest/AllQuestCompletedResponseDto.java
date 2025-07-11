package com.heartz.byeboo.adapter.in.web.dto.response.quest;

import com.heartz.byeboo.adapter.in.web.dto.response.StepResponseDto;

import java.util.List;

public record AllQuestCompletedResponseDto(
        String progressPeriod,
        Long currentStep,
        List<StepResponseDto> steps
) {
    public static AllQuestCompletedResponseDto of(
            String progressPeriod,
            Long currentStep,
            List<StepResponseDto> steps
    ) {
        return new AllQuestCompletedResponseDto(progressPeriod, currentStep, steps);
    }
}