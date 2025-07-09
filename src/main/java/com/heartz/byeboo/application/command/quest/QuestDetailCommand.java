package com.heartz.byeboo.application.command.quest;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestDetailCommand {
    private Long userId;
    private Long questId;

    public static QuestDetailCommand of(Long userId, Long questId){
        return QuestDetailCommand.builder().
                userId(userId)
                .questId(questId)
                .build();
    }
}
