package com.heartz.byeboo.application.command.usercommonquest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommonQuestDetailCommand {
    private Long userId;
    private Long answerId;

    public static CommonQuestDetailCommand from(Long userId, Long answerId){
        return CommonQuestDetailCommand.builder()
                .userId(userId)
                .answerId(answerId)
                .build();
    }
}
