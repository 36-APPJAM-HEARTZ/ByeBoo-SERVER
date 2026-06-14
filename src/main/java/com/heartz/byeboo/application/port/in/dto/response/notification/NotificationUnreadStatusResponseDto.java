package com.heartz.byeboo.application.port.in.dto.response.notification;

public record NotificationUnreadStatusResponseDto(
        boolean hasUnread
) {
    public static NotificationUnreadStatusResponseDto of(boolean hasUnread) {
        return new NotificationUnreadStatusResponseDto(hasUnread);
    }
}