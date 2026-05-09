package com.heartz.byeboo.application.port.out.notification;

import java.time.LocalDateTime;

public interface DeleteNotificationPort {
    void deleteByCreatedDateBefore(LocalDateTime threshold);
}
