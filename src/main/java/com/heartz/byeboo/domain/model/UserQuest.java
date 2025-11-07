package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EQuestEmotionState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class UserQuest {
    private Long id;
    private String answer;
    private UUID imageKey;
    private EQuestEmotionState questEmotionState;
    private User user;
    private Quest quest;
    private LocalDateTime createdDate;
    private boolean notified;

    public static UserQuest of(
            Long id,
            String answer,
            UUID imageKey,
            EQuestEmotionState questEmotionState,
            User user,
            Quest quest,
            LocalDateTime createdDate,
            boolean notified
    ){
        return UserQuest.builder()
                .id(id)
                .answer(answer)
                .imageKey(imageKey)
                .questEmotionState(questEmotionState)
                .user(user)
                .quest(quest)
                .createdDate(createdDate)
                .notified(notified)
                .build();
    }

    public static UserQuest of(
            Long id,
            String answer,
            EQuestEmotionState questEmotionState,
            User user,
            Quest quest,
            LocalDateTime createdDate,
            boolean notified
    ){
        return UserQuest.builder()
                .id(id)
                .answer(answer)
                .questEmotionState(questEmotionState)
                .user(user)
                .quest(quest)
                .createdDate(createdDate)
                .notified(notified)
                .build();
    }
}
