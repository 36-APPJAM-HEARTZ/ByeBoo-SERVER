package com.heartz.byeboo.infrastructure.dto;

import lombok.Builder;

@Builder
public record SocialInfoResponse(
        String serialId,
        String refreshToken
)
{
    public static SocialInfoResponse of(final String serialId, String refreshToken) {
        return SocialInfoResponse.builder()
                .serialId(serialId)
                .refreshToken(refreshToken)
                .build();
    }
}
