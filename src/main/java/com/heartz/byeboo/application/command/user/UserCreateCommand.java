package com.heartz.byeboo.application.command.user;

import com.heartz.byeboo.adapter.in.web.dto.request.UserCreateRequestDto;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserErrorCode;
import com.heartz.byeboo.domain.type.EFeeling;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.utils.TextUtil;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateCommand {
    private String name;
    private EFeeling feeling;
    private EQuestStyle questStyle;
    private Long userId;

    public static UserCreateCommand of(UserCreateRequestDto userCreateRequestDto, Long userId) {
        try {
            System.out.println("command]"+userId);
            validateUserName(userCreateRequestDto.name());

            return UserCreateCommand.builder()
                    .name(userCreateRequestDto.name())
                    .questStyle(EQuestStyle.valueOf(userCreateRequestDto.questStyle()))
                    .feeling(EFeeling.valueOf(userCreateRequestDto.feeling()))
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
