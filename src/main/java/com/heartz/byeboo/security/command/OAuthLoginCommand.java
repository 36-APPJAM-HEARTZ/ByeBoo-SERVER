package com.heartz.byeboo.security.command;

import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.AuthErrorCode;
import com.heartz.byeboo.domain.type.EPlatform;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthLoginCommand {

    private EPlatform platform;
    private String token;

    public static OAuthLoginCommand of(String platform, String token){
        try {
            return OAuthLoginCommand.builder()
                    .platform(EPlatform.valueOf(platform))
                    .token(token)
                    .build();
        } catch (IllegalArgumentException e){
            throw new CustomException(AuthErrorCode.INVALID_PLATFORM);
        }
    }

}
