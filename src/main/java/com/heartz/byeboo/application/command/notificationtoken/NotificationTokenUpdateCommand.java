package com.heartz.byeboo.application.command.notificationtoken;

import lombok.Builder;

@Builder
public record NotificationTokenUpdateCommand(
        Long userId,
        String notificationToken
        ) {
    public static NotificationTokenUpdateCommand from(Long userId, String notificationToken){
        return NotificationTokenUpdateCommand.builder()
                .notificationToken(notificationToken)
                .userId(userId)
                .build();
    }
}
