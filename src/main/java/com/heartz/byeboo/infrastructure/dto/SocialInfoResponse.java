package com.heartz.byeboo.infrastructure.dto;

public record SocialInfoResponse(
        String serialId
)
{
    public static SocialInfoResponse of(final String serialId) {
        return new SocialInfoResponse(serialId);
    }
}
