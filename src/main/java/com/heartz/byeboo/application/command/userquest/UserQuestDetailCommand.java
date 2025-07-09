package com.heartz.byeboo.application.command.userquest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserQuestDetailCommand {
    private Long questId;
    private Long userId;

    public static UserQuestDetailCommand of(Long questId, Long userId){
        return UserQuestDetailCommand.builder()
                .questId(questId)
                .userId(userId)
                .build();
    }
}
