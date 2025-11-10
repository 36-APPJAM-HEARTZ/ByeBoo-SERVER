package com.heartz.byeboo.application.port.out.notificationtoken;

import com.heartz.byeboo.domain.model.NotificationToken;

public interface CreateNotificationTokenPort {
    void createNotificationToken(NotificationToken notificationToken);
}
