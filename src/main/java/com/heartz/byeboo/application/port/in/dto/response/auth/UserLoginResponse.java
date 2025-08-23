package com.heartz.byeboo.application.port.in.dto.response.auth;

import com.heartz.byeboo.security.jwt.TokenResponse;

import java.io.Serializable;

public record UserLoginResponse(
        String accessToken,
        String refreshToken,
        boolean isRegistered
) implements Serializable {
    public static UserLoginResponse of(TokenResponse tokenResponse, boolean isRegistered){
        return new UserLoginResponse(tokenResponse.accessToken(), tokenResponse.refreshToken(), isRegistered);
    }
}
