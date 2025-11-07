package com.heartz.byeboo.application.command.notificationtoken;

import lombok.Builder;

@Builder
public record NotificationTokenSaveCommand(
        Long userId,
        String notificationToken
) {
    public static NotificationTokenSaveCommand from(Long userId, String notificationToken){
        return NotificationTokenSaveCommand.builder()
                .notificationToken(notificationToken)
                .userId(userId)
                .build();
    }
}
