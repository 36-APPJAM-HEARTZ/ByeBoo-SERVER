package com.heartz.byeboo.adapter.in.web.dto.response.user;

public record UserJourneyResponseDto(
        String journey,
        String description
) {
    public static UserJourneyResponseDto of(String journey, String description) {
        return new UserJourneyResponseDto(journey, description);
    }
}
