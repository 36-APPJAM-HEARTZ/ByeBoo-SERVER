package com.heartz.byeboo.adapter.out.persistence.repository.projection;

import com.heartz.byeboo.domain.type.ENotificationType;

import java.time.LocalDateTime;

public interface NotificationProjection {
    Long getNotificationId();
    String getSenderNickname();
    ENotificationType getType();
    Long getTargetId();
    String getLandingUrl();
    Boolean getIsRead();
    LocalDateTime getCreatedAt();
}


