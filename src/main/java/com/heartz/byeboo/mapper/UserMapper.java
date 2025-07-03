package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.domain.model.User;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        return UserEntity.create(
                user.getName(),
                user.getQuestStyle(),
                user.getCurrentNumber()
        );
    }
}
