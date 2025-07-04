package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.domain.model.UserQuest;

public class UserQuestMapper {
    public static UserQuestEntity toEntity(UserQuest userQuest) {
        return UserQuestEntity.create(
                userQuest.getAnswer(),
                userQuest.getImageUrl(),
                userQuest.getQuestEmotionState(),
                userQuest.getUser().getId(),
                userQuest.getQuest().getId()
        );
    }
}
