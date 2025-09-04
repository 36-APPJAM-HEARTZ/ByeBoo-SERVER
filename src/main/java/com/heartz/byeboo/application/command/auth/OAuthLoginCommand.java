package com.heartz.byeboo.application.command.auth;

import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.AuthErrorCode;
import com.heartz.byeboo.domain.type.EPlatform;
import lombok.Builder;
import lombok.Getter;

@Builder
public record OAuthLoginCommand(
        EPlatform platform,
        String token,
        String code
) {

    public static OAuthLoginCommand of(String platform, String token, String code){
        try {
            return OAuthLoginCommand.builder()
                    .platform(EPlatform.valueOf(platform))
                    .token(token)
                    .code(code)
                    .build();
        } catch (IllegalArgumentException e){
            throw new CustomException(AuthErrorCode.INVALID_PLATFORM);
        }
    }

}
