package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.application.command.userquest.ActiveQuestCreateCommand;
import com.heartz.byeboo.application.command.userquest.RecordingQuestCreateCommand;
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
                userQuest.getQuest().getId(),
                userQuest.isNotified()
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
        return UserQuest.of(
                userQuestEntity.getId(),
                userQuestEntity.getAnswer(),
                userQuestEntity.getImageKey(),
                userQuestEntity.getQuestEmotionState(),
                user,
                quest,
                userQuestEntity.getCreatedDate(),
                userQuestEntity.isNotified()
        );
    }

    public static UserQuest toDomainActive(UserQuestEntity userQuestEntity, User user, Quest quest) {
        return UserQuest.of(
                userQuestEntity.getId(),
                userQuestEntity.getAnswer(),
                userQuestEntity.getImageKey(),
                userQuestEntity.getQuestEmotionState(),
                user,
                quest,
                userQuestEntity.getCreatedDate(),
                userQuestEntity.isNotified()
        );
    }

    public static UserQuest toDomainRecording(UserQuestEntity userQuestEntity, User user, Quest quest){
        return UserQuest.of(
                userQuestEntity.getId(),
                userQuestEntity.getAnswer(),
                userQuestEntity.getQuestEmotionState(),
                user,
                quest,
                userQuestEntity.getCreatedDate(),
                userQuestEntity.isNotified()
        );
    }

}