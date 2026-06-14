package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.NotificationEntity;
import com.heartz.byeboo.domain.model.Notification;
import com.heartz.byeboo.domain.type.ENotificationType;

public class NotificationMapper {

    public static NotificationEntity toEntity(Notification notification){
        return NotificationEntity.create(notification.getLandingUrl(), notification.getTargetId(), notification.getNotificationType(), notification.getUserId(), notification.getSenderUserId());
    }

    public static Notification toDomain(Long userId, Long targetId, ENotificationType notificationType, Long senderUserId) {
        return Notification.builder()
                .userId(userId)
                .isRead(false)
                .landingUrl(notificationType.createLandingUrl(targetId))
                .targetId(targetId)
                .notificationType(notificationType)
                .senderUserId(senderUserId)
                .build();
    }

    public static Notification entityToDomain(NotificationEntity notificationEntity) {
        return Notification.builder()
                .userId(notificationEntity.getUserId())
                .id(notificationEntity.getId())
                .isRead(notificationEntity.isRead())
                .landingUrl(notificationEntity.getLandingUrl())
                .targetId(notificationEntity.getTargetId())
                .notificationType(notificationEntity.getNotificationType())
                .senderUserId(notificationEntity.getSenderUserId())
                .build();
    }

    public static NotificationEntity toEntityForUpdate(Notification notification){
        return NotificationEntity.from(notification);
    }

}
