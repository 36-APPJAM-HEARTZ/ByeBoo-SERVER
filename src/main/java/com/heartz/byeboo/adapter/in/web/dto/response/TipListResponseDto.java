package com.heartz.byeboo.adapter.in.web.dto.response;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.Tip;

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
                quest.getStepNumber(),
                quest.getStepNumber(),
                quest.getQuestion(),
                tipResponseDto
        );
    }
}