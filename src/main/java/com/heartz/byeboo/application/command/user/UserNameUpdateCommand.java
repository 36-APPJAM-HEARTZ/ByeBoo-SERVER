package com.heartz.byeboo.application.command.user;

import com.heartz.byeboo.adapter.in.web.dto.request.UserNameUpdateRequestDto;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserErrorCode;
import com.heartz.byeboo.utils.TextUtil;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserNameUpdateCommand {
    private Long id;
    private String name;

    public static UserNameUpdateCommand of(Long id, UserNameUpdateRequestDto userNameUpdateRequestDto){
        validateUserName(userNameUpdateRequestDto.name());

        return UserNameUpdateCommand.builder()
                .id(id)
                .name(userNameUpdateRequestDto.name())
                .build();
    }

    private static void validateUserName(String name){
        if(TextUtil.lengthWithEmoji(name) < 2)
            throw new CustomException(UserErrorCode.USER_NAME_TOO_SHORT);

        if(TextUtil.lengthWithEmoji(name) > 5)
            throw new CustomException(UserErrorCode.USER_NAME_TOO_LONG);
    }
}
