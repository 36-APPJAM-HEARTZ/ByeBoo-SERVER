package com.heartz.byeboo.application.command.userquest;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompletedJourneyCommand {
    private Long userId;

    public static CompletedJourneyCommand of(Long userId){
        return CompletedJourneyCommand.builder()
                .userId(userId)
                .build();
    }
}
