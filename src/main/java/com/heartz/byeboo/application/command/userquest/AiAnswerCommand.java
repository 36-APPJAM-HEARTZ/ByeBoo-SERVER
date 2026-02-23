package com.heartz.byeboo.application.command.userquest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AiAnswerCommand {
    private Long userId;
    private Long questId;

    public static AiAnswerCommand of(Long userId, Long questId){
        return AiAnswerCommand.builder()
                .userId(userId)
                .questId(questId)
                .build();
    }
}
