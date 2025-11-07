package com.heartz.byeboo.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationToken {

    private Long id;
    private String fcmToken;
    private LocalDateTime timeStamp;
    private User user;

    public static NotificationToken of(Long id, String fcmToken, LocalDateTime timeStamp, User user){
        return NotificationToken.builder()
                .fcmToken(fcmToken)
                .timeStamp(timeStamp)
                .user(user)
                .id(id)
                .build();
    }

    public void updateTimeStamp(){
        this.timeStamp = LocalDateTime.now();
    }
}
