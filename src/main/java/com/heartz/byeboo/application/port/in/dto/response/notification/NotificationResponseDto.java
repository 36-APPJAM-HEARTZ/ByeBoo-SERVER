package com.heartz.byeboo.application.port.in.dto.response.notification;

import com.heartz.byeboo.domain.type.ENotificationType;

import java.time.LocalDateTime;

public record NotificationResponseDto(
        Long notificationId,
        String content,
        String title,
        boolean isRead,
        LocalDateTime createdAt,
        String landingUrl,
        ENotificationType notificationType
) {
    public static NotificationResponseDto of(Long notificationId, String content, String title, boolean isRead, LocalDateTime createdAt, String landingUrl, ENotificationType notificationType){
        return new NotificationResponseDto(notificationId, content, title, isRead, createdAt, landingUrl, notificationType);
    }
}
