package com.heartz.byeboo.application.command.user;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJourneyUpdateCommand{
    private Long id;

    public static UserJourneyUpdateCommand of(Long id) {
        return UserJourneyUpdateCommand.builder()
                .id(id)
                .build();
    }
}
