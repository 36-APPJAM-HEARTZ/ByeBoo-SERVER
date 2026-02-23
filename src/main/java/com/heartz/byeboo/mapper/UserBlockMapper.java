package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserBlockEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.application.command.userblock.UserBlockCommand;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserBlock;
import com.heartz.byeboo.domain.model.UserCommonQuest;

public class UserBlockMapper {

    public static UserBlockEntity toEntity(UserBlock userBlock) {
        return UserBlockEntity.create(
                userBlock.getBlockerUser().getId(),
                userBlock.getBlockedUser().getId()
        );
    }

    public static UserBlock usersToDomain(User blockedUser, User blockerUser){
        return UserBlock.builder()
                .blockedUser(blockedUser)
                .blockerUser(blockerUser)
                .build();
    }
}
