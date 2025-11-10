package com.heartz.byeboo.application.port.out.notificationtoken;

import com.heartz.byeboo.domain.model.NotificationToken;
import com.heartz.byeboo.domain.model.User;

public interface UpdateNotificationTokenPort {
    void updateTimeStamp(NotificationToken notificationToken, User user);
}
