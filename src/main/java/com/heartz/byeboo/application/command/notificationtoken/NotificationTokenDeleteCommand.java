package com.heartz.byeboo.application.command.notificationtoken;

import lombok.Builder;

@Builder
public record NotificationTokenDeleteCommand(
        Long userId,
        String notificationToken
) {
    public static NotificationTokenDeleteCommand of(Long userId, String notificationToken){
        return NotificationTokenDeleteCommand.builder()
                .notificationToken(notificationToken)
                .userId(userId)
                .build();
    }
}
