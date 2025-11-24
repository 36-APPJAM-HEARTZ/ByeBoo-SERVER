package com.heartz.byeboo.application.port.in.dto.response.userquest;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.UserQuest;

import java.time.LocalDate;

public record UserQuestDetailResponseDto(
        Long stepNumber,
        Long questNumber,
        LocalDate createdAt,
        String question,
        String answer,
        String questEmotionState,
        String imageKey,
        String imageUrl,
        String emotionDescription
) {
    public static UserQuestDetailResponseDto of(UserQuest userQuest, Quest quest, String signedUrl){
        return new UserQuestDetailResponseDto(
                quest.getStep().getStepNumber(),
                quest.getQuestNumber(),
                userQuest.getCreatedDate().toLocalDate(),
                quest.getQuestion(),
                userQuest.getAnswer(),
                userQuest.getQuestEmotionState().getLabel(),
                userQuest.getImageKey().toString(),
                signedUrl,
                userQuest.getQuestEmotionState().getDescription()
                );
    }

    public static UserQuestDetailResponseDto of(UserQuest userQuest, Quest quest){
        return new UserQuestDetailResponseDto(
                quest.getStep().getStepNumber(),
                quest.getQuestNumber(),
                userQuest.getCreatedDate().toLocalDate(),
                quest.getQuestion(),
                userQuest.getAnswer(),
                userQuest.getQuestEmotionState().getLabel(),
                null,
                null,
                userQuest.getQuestEmotionState().getDescription()
        );
    }
}