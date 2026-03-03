package com.heartz.byeboo.application.command.user;

import com.heartz.byeboo.adapter.in.web.dto.request.UserCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.UserCreateRequestV2Dto;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserErrorCode;
import com.heartz.byeboo.domain.type.EFeeling;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.utils.TextUtil;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCreateV2Command {
    private String name;
    private Long userId;
    private EQuestStyle questStyle;

    public static UserCreateV2Command of(UserCreateRequestV2Dto userCreateRequestDto, Long userId) {
        try {
            validateUserName(userCreateRequestDto.name());

            return UserCreateV2Command.builder()
                    .name(userCreateRequestDto.name())
                    .questStyle(EQuestStyle.valueOf(userCreateRequestDto.questStyle()))
                    .userId(userId)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new CustomException(UserErrorCode.INVALID_QUEST_STYLE);
        }
    }

    private static void validateUserName(String name){
        if(TextUtil.lengthWithEmoji(name) < 2)
            throw new CustomException(UserErrorCode.USER_NAME_TOO_SHORT);

        if(TextUtil.lengthWithEmoji(name) > 5)
            throw new CustomException(UserErrorCode.USER_NAME_TOO_LONG);
    }
}
