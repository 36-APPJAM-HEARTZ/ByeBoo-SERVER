package com.heartz.byeboo.application.port.out.notification;

import com.heartz.byeboo.domain.model.Notification;

public interface UpdateNotificationPort {
    void updateIsRead(Notification notification);
}
