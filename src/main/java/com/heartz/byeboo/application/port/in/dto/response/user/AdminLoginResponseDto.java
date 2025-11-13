package com.heartz.byeboo.application.port.in.dto.response.user;

import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import com.heartz.byeboo.security.jwt.TokenResponse;

import java.io.Serializable;

public record AdminLoginResponseDto(
        String accessToken,
        String refreshToken,
        boolean isRegistered,
        String name,
        EJourney journey,
        EJourneyStatus journeyStatus,
        Long userId
) implements Serializable {
    public static AdminLoginResponseDto of(
            TokenResponse tokenResponse,
            boolean isRegistered,
            String name,
            EJourney journey,
            EJourneyStatus journeyStatus,
            Long userId
    ){
        return new AdminLoginResponseDto(
                tokenResponse.accessToken(),
                tokenResponse.refreshToken(),
                isRegistered,
                name,
                journey,
                journeyStatus,
                userId
        );
    }
}
