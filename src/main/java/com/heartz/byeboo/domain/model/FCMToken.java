package com.heartz.byeboo.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FCMToken {

    private Long id;
    private String fcmToken;
    private LocalDateTime timeStamp;
    private Long userId;

    public static FCMToken of(Long id, String fcmToken, LocalDateTime timeStamp, Long userId){
        return FCMToken.builder()
                .fcmToken(fcmToken)
                .timeStamp(timeStamp)
                .userId(userId)
                .id(id)
                .build();
    }
}
