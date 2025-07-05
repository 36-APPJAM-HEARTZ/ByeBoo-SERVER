package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.application.command.UserCreateCommand;
import com.heartz.byeboo.domain.model.User;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        return UserEntity.create(
                user.getName(),
                user.getQuestStyle(),
                user.getCurrentNumber()
        );
    }

    public static User toDomain(UserEntity userEntity) {
        return User.of(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getQuestStyle(),
                userEntity.getCurrentNumber()
        );
    }

    public static User commandToDomain(UserCreateCommand userCreateCommand) {
        return User.builder()
                .name(userCreateCommand.getName())
                .questStyle(userCreateCommand.getQuestStyle())
                .build();
    }

}
