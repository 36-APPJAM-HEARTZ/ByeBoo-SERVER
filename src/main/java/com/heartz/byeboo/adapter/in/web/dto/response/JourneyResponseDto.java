package com.heartz.byeboo.adapter.in.web.dto.response;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EQuestStyle;

public record JourneyResponseDto(
        String journey,
        String style
) {
    public static JourneyResponseDto from(EJourney journey, EQuestStyle questStyle){
        return new JourneyResponseDto(
                journey.getLabel(),
                questStyle.toString()
        );
    }
}