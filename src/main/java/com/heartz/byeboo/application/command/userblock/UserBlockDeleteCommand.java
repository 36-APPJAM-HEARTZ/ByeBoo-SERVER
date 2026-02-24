package com.heartz.byeboo.application.command.userblock;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserBlockDeleteCommand {
    private Long userId;
    private Long blockId;

    public static UserBlockDeleteCommand from(Long userId, Long blockId){
        return UserBlockDeleteCommand.builder()
                .userId(userId)
                .blockId(blockId)
                .build();
    }
}
