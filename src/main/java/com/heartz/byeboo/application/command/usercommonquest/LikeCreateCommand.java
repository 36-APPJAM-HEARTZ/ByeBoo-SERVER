package com.heartz.byeboo.application.command.usercommonquest;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeCreateCommand {
    private Long userId;
    private Long userCommonQuestId;

    public static LikeCreateCommand of(Long userId, Long userCommonQuestId){
        return LikeCreateCommand.builder()
                .userId(userId)
                .userCommonQuestId(userCommonQuestId)
                .build();
    }
}
