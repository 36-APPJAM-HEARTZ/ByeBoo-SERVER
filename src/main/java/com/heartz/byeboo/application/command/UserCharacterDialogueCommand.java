package com.heartz.byeboo.application.command;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCharacterDialogueCommand {
    private Long id;

    public static UserCharacterDialogueCommand of(Long id) {
        return UserCharacterDialogueCommand.builder()
                .id(id)
                .build();
    }
}
