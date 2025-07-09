package com.heartz.byeboo.adapter.in.web.dto.response.userquest;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.UserQuest;

import java.time.LocalDate;

public record UserQuestDetailResponseDto(
        Integer stepNumber,
        Long questNumber,
        LocalDate createdAt,
        String question,
        String answer,
        String questEmotionState,
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
                signedUrl,
                userQuest.getQuestEmotionState().getDescription()
                );
    }
}