package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.ENotificationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notifications")
public class NotificationEntity extends BaseEntity{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "landing_url", nullable = false)
    private String landingUrl;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false, length = 50)
    private ENotificationType notificationType;

    @Column(name = "sender_user_id")
    private Long senderUserId;

    @Builder
    public NotificationEntity(Long id, String landingUrl, Long targetId, ENotificationType notificationType, Long userId, Long senderUserId) {
        this.userId = userId;
        this.id = id;
        this.landingUrl = landingUrl;
        this.isRead = false;
        this.targetId = targetId;
        this.notificationType = notificationType;
        this.senderUserId = senderUserId;
    }

    public static NotificationEntity create(String landingUrl, Long targetId, ENotificationType notificationType, Long userId, Long senderUserId) {
        return NotificationEntity.builder()
                .landingUrl(landingUrl)
                .userId(userId)
                .targetId(targetId)
                .notificationType(notificationType)
                .senderUserId(senderUserId)
                .build();
    }
}
