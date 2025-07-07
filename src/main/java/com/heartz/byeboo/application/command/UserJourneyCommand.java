package com.heartz.byeboo.application.command;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJourneyCommand {
    private Long id;

    public static UserJourneyCommand of(Long id) {
        return UserJourneyCommand.builder()
                .id(id)
                .build();
    }
}
