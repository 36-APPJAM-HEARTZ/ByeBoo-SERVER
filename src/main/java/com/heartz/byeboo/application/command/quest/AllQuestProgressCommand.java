package com.heartz.byeboo.application.command.quest;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllQuestProgressCommand {
    private Long userId;

    public static AllQuestProgressCommand of(Long userId) {
        return AllQuestProgressCommand.builder()
                .userId(userId)
                .build();
    }
}
