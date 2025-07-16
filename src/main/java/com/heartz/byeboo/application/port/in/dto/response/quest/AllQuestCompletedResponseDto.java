package com.heartz.byeboo.application.port.in.dto.response.quest;

import com.heartz.byeboo.application.port.in.dto.response.StepResponseDto;

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