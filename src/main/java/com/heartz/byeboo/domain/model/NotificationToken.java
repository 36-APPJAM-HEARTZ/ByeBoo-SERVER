package com.heartz.byeboo.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationToken {

    private Long id;
    private String notificationToken;
    private LocalDateTime timeStamp;
    private User user;

    public static NotificationToken of(Long id, String notificationToken, LocalDateTime timeStamp, User user){
        return NotificationToken.builder()
                .notificationToken(notificationToken)
                .timeStamp(timeStamp)
                .user(user)
                .id(id)
                .build();
    }

    public void updateTimeStamp(){
        this.timeStamp = LocalDateTime.now();
    }
}
