package com.heartz.byeboo.application.port.in.dto.response.auth;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.security.jwt.TokenResponse;

import java.io.Serializable;

public record UserLoginResponse(
        String accessToken,
        String refreshToken,
        boolean isRegistered,
        String name,
        EJourney journey
) implements Serializable {
    public static UserLoginResponse of(TokenResponse tokenResponse, boolean isRegistered, String name, EJourney journey){
        return new UserLoginResponse(
                tokenResponse.accessToken(),
                tokenResponse.refreshToken(),
                isRegistered,
                name,
                journey
        );
    }
}
