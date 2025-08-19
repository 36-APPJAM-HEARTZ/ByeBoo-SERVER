package com.heartz.byeboo.application.port.in.dto.response.auth;

import com.heartz.byeboo.domain.type.EPlatform;

public record UserInfoResponse(
        EPlatform platform,
        String serialId
) {
    public static UserInfoResponse of(EPlatform platform, String serialId){
        return new UserInfoResponse(platform, serialId);
    }
}
