package com.heartz.byeboo.application.command.userblock;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserBlockCommand {
    private Long userId;
    private Long targetUserId;

    public static UserBlockCommand from(Long userId, Long targetUserId){
        return UserBlockCommand.builder()
                .userId(userId)
                .targetUserId(targetUserId)
                .build();
    }
}
