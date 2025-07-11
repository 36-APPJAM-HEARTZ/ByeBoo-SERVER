package com.heartz.byeboo.adapter.in.web.dto.response.quest;

import com.heartz.byeboo.adapter.in.web.dto.response.StepResponseDto;

import java.util.List;

public record AllQuestProgressResponseDto(
        Long progressPeriod,
        Long currentStep,
        List<StepResponseDto> steps
) {
    public static AllQuestProgressResponseDto of(
            Long progressPeriod,
            Long currentStep,
            List<StepResponseDto> steps
    ) {
        return new AllQuestProgressResponseDto(progressPeriod, currentStep, steps);
    }
}
