package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.UserCreateRequestDto;
import com.heartz.byeboo.domain.type.EQuestStyle;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateCommand {
    private String name;
    private EQuestStyle questStyle;

    public static UserCreateCommand from(UserCreateRequestDto userCreateRequestDto) {
        return UserCreateCommand.builder()
                .name(userCreateRequestDto.name())
                .questStyle(userCreateRequestDto.questStyle())
                .build();
    }
}
