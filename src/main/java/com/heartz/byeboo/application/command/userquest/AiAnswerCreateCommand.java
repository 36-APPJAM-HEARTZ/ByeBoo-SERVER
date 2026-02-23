package com.heartz.byeboo.application.command.userquest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AiAnswerCreateCommand {
    private Long userId;
    private Long questId;

    public static AiAnswerCreateCommand of(Long userId, Long questId){
        return AiAnswerCreateCommand.builder()
                .userId(userId)
                .questId(questId)
                .build();
    }
}
