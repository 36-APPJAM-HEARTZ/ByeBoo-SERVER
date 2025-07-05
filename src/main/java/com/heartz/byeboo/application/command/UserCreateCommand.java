package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.UserCreateRequestDto;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserErrorCode;
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
        try {
            return UserCreateCommand.builder()
                    .name(userCreateRequestDto.name())
                    .questStyle(EQuestStyle.valueOf(userCreateRequestDto.questStyle()))
                    .build();
        } catch (IllegalArgumentException e) {
            throw new CustomException(UserErrorCode.INVALID_QUEST_STYLE);
        }
    }
}
