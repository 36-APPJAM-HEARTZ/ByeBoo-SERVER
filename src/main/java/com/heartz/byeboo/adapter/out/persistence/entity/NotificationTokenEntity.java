package com.heartz.byeboo.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notification_token")
public class NotificationTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "notification_token", nullable = false)
    private String notificationToken;

    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timeStamp;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Builder
    public NotificationTokenEntity(Long id, String notificationToken, LocalDateTime timeStamp, Long userId) {
        this.notificationToken = notificationToken;
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.id = id;
    }

    public static NotificationTokenEntity create(String notificationToken, LocalDateTime timeStamp, Long userId) {
        return NotificationTokenEntity.builder()
                .notificationToken(notificationToken)
                .timeStamp(timeStamp)
                .userId(userId)
                .build();
    }

    public static NotificationTokenEntity createForUpdate(Long id,String notificationToken, LocalDateTime timeStamp, Long userId){
        return NotificationTokenEntity.builder()
                .notificationToken(notificationToken)
                .id(id)
                .timeStamp(timeStamp)
                .userId(userId)
                .build();
    }
}
