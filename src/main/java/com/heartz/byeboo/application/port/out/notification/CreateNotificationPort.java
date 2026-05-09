package com.heartz.byeboo.application.port.out.notification;

import com.heartz.byeboo.domain.model.Notification;

public interface CreateNotificationPort {
    void create(Notification notification);
}
