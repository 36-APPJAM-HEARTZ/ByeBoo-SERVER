package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EQuestEmotionState;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserQuest {
    private Long id;
    private String answer;
    private String imageUrl;
    private EQuestEmotionState questEmotionState;
    private Long userId;
    private Long questId;
}
