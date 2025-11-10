package com.heartz.byeboo.application.command.notificationtoken;

import lombok.Builder;

@Builder
public record NotificationTokenSaveCommand(
        Long userId,
        String notificationToken
) {
    public static NotificationTokenSaveCommand of(Long userId, String notificationToken){
        return NotificationTokenSaveCommand.builder()
                .notificationToken(notificationToken)
                .userId(userId)
                .build();
    }
}
