package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.domain.type.EQuestEmotionState;

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

    public static UserQuest commandToDomain(RecordingQuestCreateCommand recordingQuestCreateCommand, User user, Quest quest) {
        return UserQuest.builder()
                .answer(recordingQuestCreateCommand.getAnswer())
                .imageUrl(null)
                .questEmotionState(EQuestEmotionState.valueOf(recordingQuestCreateCommand.getQuestEmotionState()))
                .user(user)
                .quest(quest)
                .build();
    }
}