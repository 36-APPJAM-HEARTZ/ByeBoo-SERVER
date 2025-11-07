package com.heartz.byeboo.application.command.user;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlarmPermissionCommand {
    private Long userId;

    public static AlarmPermissionCommand of(Long userId){
        return AlarmPermissionCommand.builder()
                .userId(userId)
                .build();
    }
}
