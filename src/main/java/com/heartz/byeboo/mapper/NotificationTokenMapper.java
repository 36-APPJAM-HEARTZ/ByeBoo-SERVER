package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.NotificationTokenEntity;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenSaveCommand;
import com.heartz.byeboo.domain.model.NotificationToken;
import com.heartz.byeboo.domain.model.User;

import java.time.LocalDateTime;

public class NotificationTokenMapper {

    public static NotificationTokenEntity toEntity(NotificationToken notificationToken){
        return NotificationTokenEntity.create(
                notificationToken.getNotificationToken(),
                notificationToken.getTimeStamp(),
                notificationToken.getUser().getId()
        );
    }

    public static NotificationToken commandToDomain(NotificationTokenSaveCommand command, User user) {
        return NotificationToken.builder()
                .notificationToken(command.notificationToken())
                .user(user)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static NotificationToken toDomain(NotificationTokenEntity notificationTokenEntity, User user) {
        return NotificationToken.of(
                notificationTokenEntity.getId(),
                notificationTokenEntity.getNotificationToken(),
                notificationTokenEntity.getTimeStamp(),
                user
        );
    }

    public static NotificationTokenEntity toEntityForUpdate(NotificationToken notificationToken, User user){
        return NotificationTokenEntity.createForUpdate(
                notificationToken.getId(),
                notificationToken.getNotificationToken(),
                notificationToken.getTimeStamp(),
                notificationToken.getUser().getId()
        );
    }
}
