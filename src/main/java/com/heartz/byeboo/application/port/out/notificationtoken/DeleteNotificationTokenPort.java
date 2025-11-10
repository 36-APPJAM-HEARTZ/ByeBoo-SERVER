package com.heartz.byeboo.application.port.out.notificationtoken;

import java.time.LocalDateTime;

public interface DeleteNotificationTokenPort {
    void deleteNotificationToken(String notificationToken, Long userId);
    void deleteAllByUserId(Long userId);
    void deleteByConnectedAtBefore(LocalDateTime threshold);
}
