package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.ENotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Notification {
    private Long userId;
    private Long id;
    private String landingUrl;
    private boolean isRead;
    private Long targetId;
    private ENotificationType notificationType;
    private Long senderUserId;

    public static Notification of(Long userId, Long id, String landingUrl, boolean isRead, Long targetId, ENotificationType notificationType, Long senderUserId){
        return Notification.builder()
                .id(id)
                .userId(userId)
                .isRead(isRead)
                .landingUrl(landingUrl)
                .targetId(targetId)
                .notificationType(notificationType)
                .senderUserId(senderUserId)
                .build();
    }

    public void read(){
        this.isRead = true;
    }
}
