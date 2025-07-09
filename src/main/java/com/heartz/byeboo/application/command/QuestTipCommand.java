package com.heartz.byeboo.application.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestTipCommand {
    private Long userId;
    private Long questId;

    public static QuestTipCommand of(Long userId, Long questId){
        return QuestTipCommand.builder().
                userId(userId)
                .questId(questId)
                .build();
    }
}
