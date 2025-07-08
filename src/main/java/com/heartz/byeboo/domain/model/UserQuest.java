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
}
