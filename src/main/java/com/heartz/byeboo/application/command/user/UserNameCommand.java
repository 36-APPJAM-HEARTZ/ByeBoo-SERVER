package com.heartz.byeboo.application.command.user;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserNameCommand {
    private Long id;

    public static UserNameCommand of(Long id) {
        return UserNameCommand.builder()
                .id(id)
                .build();
    }
}
