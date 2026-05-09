package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.port.in.dto.response.notification.NotificationListResponseDto;

public interface NotificationUseCase {
    NotificationListResponseDto getListNotification(Long userId);
    void cleanUpOldNotification();
}
