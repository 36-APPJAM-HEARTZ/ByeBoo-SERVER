package com.heartz.byeboo.application.port.in.dto.response.quest;

import com.heartz.byeboo.domain.model.Quest;

import java.util.List;

public record TipListResponseDto(
        String step,
        Long stepNumber,
        Long questNumber,
        String question,
        List<TipResponseDto> tips
) {
    public static TipListResponseDto of(Quest quest, List<TipResponseDto> tipResponseDto){
        return new TipListResponseDto(
                quest.getStep().getLabel(),
                quest.getStep().getStepNumber(),
                quest.getQuestNumber(),
                quest.getQuestion(),
                tipResponseDto
        );
    }
}