package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestCreateCommand;
import com.heartz.byeboo.domain.model.*;

public class UserCommonQuestMapper {

    public static UserCommonQuest commandToDomain(CommonQuestCreateCommand command, User user, CommonQuest commonQuest) {
        return UserCommonQuest.builder()
                .answer(command.getAnswer())
                .user(user)
                .commonQuest(commonQuest)
                .build();
    }

    public static UserCommonQuestEntity toEntity(UserCommonQuest userCommonQuest) {
        return UserCommonQuestEntity.create(
                userCommonQuest.getAnswer(),
                userCommonQuest.getUser().getId(),
                userCommonQuest.getCommonQuest().getId()
        );
    }

    public static UserCommonQuest toDomain(UserCommonQuestEntity userCommonQuestEntity, User user, CommonQuest commonQuest){
        return UserCommonQuest.of(
                userCommonQuestEntity.getId(),
                userCommonQuestEntity.getAnswer(),
                user,
                commonQuest,
                userCommonQuestEntity.getCreatedDate()
        );
    }
}
