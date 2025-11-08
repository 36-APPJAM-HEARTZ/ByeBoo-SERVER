package com.heartz.byeboo.application.port.out.notificationtoken;

import com.heartz.byeboo.adapter.out.persistence.entity.NotificationTokenEntity;
import com.heartz.byeboo.domain.model.NotificationToken;
import com.heartz.byeboo.domain.model.User;

import java.util.Optional;

public interface RetrieveNotificationTokenPort {
    NotificationToken getNotificationTokenByToken(String notificationToken, User user);
    Optional<NotificationTokenEntity> findByUserId(Long userId);
}
