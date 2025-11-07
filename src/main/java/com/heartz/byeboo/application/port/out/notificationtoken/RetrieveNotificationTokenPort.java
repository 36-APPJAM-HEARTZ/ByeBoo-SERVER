package com.heartz.byeboo.application.port.out.notificationtoken;

import com.heartz.byeboo.domain.model.NotificationToken;
import com.heartz.byeboo.domain.model.User;

public interface RetrieveNotificationTokenPort {
    NotificationToken getNotificationTokenByToken(String notificationToken, User user);
}
