package com.heartz.byeboo.application.port.in.dto.response.auth;

import com.heartz.byeboo.security.jwt.Token;

import java.io.Serializable;

public record UserLoginResponse(
        String accessToken,
        String refreshToken,
        boolean isRegistered
) implements Serializable {
    public static UserLoginResponse of(Token token, boolean isRegistered){
        return new UserLoginResponse(token.accessToken(), token.refreshToken(), isRegistered);
    }
}
