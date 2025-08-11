package com.heartz.byeboo.application.port.in.dto.response.quest;

import com.heartz.byeboo.application.port.in.dto.response.StepResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record AllQuestProgressResponseDto(
        Long progressPeriod,
        Long currentStep,
        LocalDateTime questOpenTime,
        List<StepResponseDto> steps
) {
    public static AllQuestProgressResponseDto of(
            Long progressPeriod,
            Long currentStep,
            LocalDateTime questOpenTime,
            List<StepResponseDto> steps
    ) {
        return new AllQuestProgressResponseDto(progressPeriod, currentStep, questOpenTime, steps);
    }
}
