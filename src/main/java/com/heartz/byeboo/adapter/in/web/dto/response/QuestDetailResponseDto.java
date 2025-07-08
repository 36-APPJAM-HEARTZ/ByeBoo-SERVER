package com.heartz.byeboo.adapter.in.web.dto.response;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.UserQuest;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record QuestDetailResponseDto(
        Long stepNumber,
        Long questNumber,
        LocalDate createdAt,
        String question,
        String answer,
        String questEmotionState,
        String imageUrl
) {
    public static QuestDetailResponseDto of(UserQuest userQuest, Quest quest, String signedUrl){
        return new QuestDetailResponseDto(
                quest.getStepNumber(),
                quest.getQuestNumber(),
                userQuest.getCreatedDate().toLocalDate(),
                quest.getQuestion(),
                userQuest.getAnswer(),
                userQuest.getQuestEmotionState().getLabel(),
                signedUrl
                );
    }
}