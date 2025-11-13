package com.heartz.byeboo.application.port.in.dto.response.user;

import lombok.Builder;

@Builder
public record AlarmEnabledResponseDto(
        boolean alarmEnabled
) {
    public static AlarmEnabledResponseDto of(boolean alarmState){
        return AlarmEnabledResponseDto.builder()
                .alarmEnabled(alarmState)
                .build();
    }
}
