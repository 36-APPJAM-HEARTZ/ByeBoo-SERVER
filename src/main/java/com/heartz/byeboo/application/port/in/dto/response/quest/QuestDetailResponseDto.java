package com.heartz.byeboo.application.port.in.dto.response.quest;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.type.EQuestStyle;

public record QuestDetailResponseDto(
        String step,
        Long stepNumber,
        Long questNumber,
        EQuestStyle questStyle,
        String question
) {
    public static QuestDetailResponseDto from(Quest quest) {
        return new QuestDetailResponseDto(
                quest.getStep().getLabel(),
                quest.getStep().getStepNumber(),
                quest.getQuestNumber(),
                quest.getQuestStyle(),
                quest.getQuestion()
        );
    }
}
