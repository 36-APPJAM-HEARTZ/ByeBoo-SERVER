package com.heartz.byeboo.infrastructure.dto.apple;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AppleTokenResponse(
        String accessToken,
        String tokenType,
        String expiresIn,
        String refreshToken,
        String idToken
) {
}
