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
                user.getJourney(),
                user.getCurrentNumber(),
                user.getPlatform(),
                user.getRole(),
                user.getSerialId(),
                user.getStatus(),
                user.getDeletedAt(),
                user.getRefreshToken(),
                user.isAlarmEnabled()
        );
    }

    public static User toDomain(UserEntity userEntity) {
        return User.of(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getQuestStyle(),
                userEntity.getJourney(),
                userEntity.getCurrentNumber(),
                userEntity.getPlatform(),
                userEntity.getRole(),
                userEntity.getSerialId(),
                userEntity.getStatus(),
                userEntity.getDeletedAt(),
                userEntity.getRefreshToken(),
                userEntity.isAlarmEnabled()
        );
    }

    public static UserEntity toEntityForUpdate(User user) {
        return UserEntity.createForUpdate(
                user.getId(),
                user.getName(),
                user.getQuestStyle(),
                user.getJourney(),
                user.getCurrentNumber(),
                user.getPlatform(),
                user.getRole(),
                user.getSerialId(),
                user.getStatus(),
                user.getDeletedAt(),
                user.getRefreshToken(),
                user.isAlarmEnabled()
        );
    }

    public static User userInfoToDomain(String serialId, EPlatform platform, ERole role, String refreshToken){
        return User.builder()
                .serialId(serialId)
                .platform(platform)
                .role(role)
                .refreshToken(refreshToken)
                .build();
    }

}
