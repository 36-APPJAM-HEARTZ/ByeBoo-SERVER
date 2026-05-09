package com.heartz.byeboo.application.port.out.notification;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.NotificationProjection;
import com.heartz.byeboo.domain.model.Notification;

import java.util.List;

public interface RetrieveNotificationPort {
    List<NotificationProjection> getNotificationByUserId(Long userId);
}
