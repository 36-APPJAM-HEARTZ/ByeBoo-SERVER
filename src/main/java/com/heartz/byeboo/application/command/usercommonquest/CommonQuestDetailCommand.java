package com.heartz.byeboo.application.command.usercommonquest;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
