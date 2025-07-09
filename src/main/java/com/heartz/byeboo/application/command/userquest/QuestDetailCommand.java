package com.heartz.byeboo.application.command.userquest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestDetailCommand {
    private Long questId;
    private Long userId;

    public static QuestDetailCommand of(Long questId, Long userId){
        return QuestDetailCommand.builder()
                .questId(questId)
                .userId(userId)
                .build();
    }
}
