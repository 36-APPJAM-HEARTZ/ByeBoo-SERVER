package com.heartz.byeboo.application.port.in.dto.response.notification;

import java.util.List;

public record NotificationListResponseDto(
        List<NotificationResponseDto> notifications
) {
    public static NotificationListResponseDto from(List<NotificationResponseDto> notifications){
        return new NotificationListResponseDto(notifications);

    }
}
