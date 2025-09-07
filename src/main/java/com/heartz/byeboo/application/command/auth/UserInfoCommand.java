package com.heartz.byeboo.application.command.auth;

import com.heartz.byeboo.domain.type.EPlatform;
import lombok.Builder;
import lombok.Getter;

@Builder
public record UserInfoCommand(

        EPlatform platform,
        String token,
        String code
) {
    public static UserInfoCommand of(EPlatform platform, String token, String code){
            return UserInfoCommand.builder()
                    .platform(platform)
                    .token(token)
                    .code(code)
                    .build();
    }
}
