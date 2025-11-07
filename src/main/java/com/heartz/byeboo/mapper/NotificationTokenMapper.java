package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.NotificationTokenEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenSaveCommand;
import com.heartz.byeboo.application.command.userquest.RecordingQuestCreateCommand;
import com.heartz.byeboo.domain.model.NotificationToken;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserQuest;

import java.time.LocalDateTime;

public class NotificationTokenMapper {

    public static NotificationTokenEntity toEntity(NotificationToken notificationToken){
        return NotificationTokenEntity.create(
                notificationToken.getFcmToken(),
                notificationToken.getTimeStamp(),
                notificationToken.getUser().getId()
        );
    }

    public static NotificationToken commandToDomain(NotificationTokenSaveCommand command, User user) {
        return NotificationToken.builder()
                .fcmToken(command.notificationToken())
                .user(user)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static NotificationToken toDomain(NotificationTokenEntity notificationTokenEntity, User user) {
        return NotificationToken.of(
                notificationTokenEntity.getId(),
                notificationTokenEntity.getFcmToken(),
                notificationTokenEntity.getTimeStamp(),
                user
        );
    }

    public static NotificationTokenEntity toEntityForUpdate(NotificationToken notificationToken, User user){
        return NotificationTokenEntity.createForUpdate(
                notificationToken.getId(),
                notificationToken.getFcmToken(),
                notificationToken.getTimeStamp(),
                notificationToken.getUser().getId()
        );
    }
}
