package com.heartz.byeboo.application.port.in.dto.response.userquest;

import java.util.List;

public record JourneyListResponseDto(
        Integer inCompletedCount,
        List<JourneyResponseDto> inCompletedJourneys,
        Integer completedCount,
        List<JourneyResponseDto> completedJourneys
) {
    public static JourneyListResponseDto of(int inCompletedCount, List<JourneyResponseDto> inCompletedJourneys, int completedCount, List<JourneyResponseDto> completedJourneys){
        return new JourneyListResponseDto(
                inCompletedCount,
                inCompletedJourneys,
                completedCount,
                completedJourneys
        );
    }
}
