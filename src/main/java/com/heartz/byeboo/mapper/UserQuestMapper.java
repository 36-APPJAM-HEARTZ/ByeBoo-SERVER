package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.application.command.ActiveQuestCreateCommand;
import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserQuest;

public class UserQuestMapper {
    public static UserQuestEntity toEntity(UserQuest userQuest) {
        return UserQuestEntity.create(
                userQuest.getAnswer(),
                userQuest.getImageKey(),
                userQuest.getQuestEmotionState(),
                userQuest.getUser().getId(),
                userQuest.getQuest().getId()
        );
    }

    public static UserQuest commandToDomainRecording(RecordingQuestCreateCommand command, User user, Quest quest) {
        return UserQuest.builder()
                .answer(command.getAnswer())
                .questEmotionState(command.getQuestEmotionState())
                .user(user)
                .quest(quest)
                .build();
    }

    public static UserQuest commandToDomainActive(ActiveQuestCreateCommand command, User user, Quest quest){
        return UserQuest.builder()
                .answer(command.getAnswer())
                .questEmotionState(command.getQuestEmotionState())
                .user(user)
                .quest(quest)
                .imageKey(command.getImageKey())
                .build();
    }

    public static UserQuest toDomain(UserQuestEntity userQuestEntity, User user, Quest quest) {
        return UserQuest.builder()
                .id(userQuestEntity.getId())
                .answer(userQuestEntity.getAnswer())
                .questEmotionState(userQuestEntity.getQuestEmotionState())
                .imageKey(userQuestEntity.getImageKey())
                .user(user)
                .quest(quest)
                .createdDate(userQuestEntity.getCreatedDate())
                .build();
    }
}