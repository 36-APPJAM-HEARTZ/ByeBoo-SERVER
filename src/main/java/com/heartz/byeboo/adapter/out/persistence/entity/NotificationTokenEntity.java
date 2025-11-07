package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.model.NotificationToken;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "fcm_token")
public class NotificationTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fcm_token", nullable = false)
    private String fcmToken;

    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timeStamp;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Builder
    public NotificationTokenEntity(Long id, String fcmToken, LocalDateTime timeStamp, Long userId) {
        this.fcmToken = fcmToken;
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.id = id;
    }

    public static NotificationTokenEntity create(String fcmToken, LocalDateTime timeStamp, Long userId) {
        return NotificationTokenEntity.builder()
                .fcmToken(fcmToken)
                .timeStamp(timeStamp)
                .userId(userId)
                .build();
    }

    public static NotificationTokenEntity createForUpdate(Long id,String fcmToken, LocalDateTime timeStamp, Long userId){
        return NotificationTokenEntity.builder()
                .fcmToken(fcmToken)
                .id(id)
                .timeStamp(timeStamp)
                .userId(userId)
                .build();
    }
}
