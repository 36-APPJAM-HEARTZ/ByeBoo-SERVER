package com.heartz.byeboo.application.port.in.dto.response.auth;

import com.heartz.byeboo.security.jwt.TokenResponse;

public record UserReissueResponse(
        String accessToken,
        String refreshToken
) {
    public static UserReissueResponse from(TokenResponse tokenResponse){
        return new UserReissueResponse(tokenResponse.accessToken(), tokenResponse.refreshToken());
    }
}
