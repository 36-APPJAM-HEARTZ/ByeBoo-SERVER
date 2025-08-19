package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.application.command.user.UserCreateCommand;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.type.EPlatform;
import com.heartz.byeboo.domain.type.ERole;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        return UserEntity.create(
                user.getName(),
                user.getQuestStyle(),
                user.getCurrentNumber(),
                user.getPlatform(),
                user.getRole(),
                user.getSerialId()
        );
    }

    public static User toDomain(UserEntity userEntity) {
        return User.of(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getQuestStyle(),
                userEntity.getCurrentNumber(),
                userEntity.getPlatform(),
                userEntity.getRole(),
                userEntity.getSerialId()
        );
    }

    public static User commandToDomain(UserCreateCommand userCreateCommand) {
        return User.builder()
                .name(userCreateCommand.getName())
                .questStyle(userCreateCommand.getQuestStyle())
                .build();
    }

    public static UserEntity toEntityForUpdate(User user) {
        return UserEntity.createForUpdate(
                user.getId(),
                user.getName(),
                user.getQuestStyle(),
                user.getCurrentNumber()
        );
    }

    public static User userInfoToDomain(String serialId, EPlatform platform, ERole role){
        return User.builder()
                .serialId(serialId)
                .platform(platform)
                .role(role)
                .build();
    }

}
