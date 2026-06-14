package com.heartz.byeboo.application.command.notification;

public record NotificationUpdateCommand(
        Long userId,
        Long notificationId
) {
    public static NotificationUpdateCommand of(Long userId, Long notificationId){
        return new NotificationUpdateCommand(userId, notificationId);
    }
}
