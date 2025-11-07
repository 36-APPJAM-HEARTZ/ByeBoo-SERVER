package com.heartz.byeboo.application.port.in.dto.response.user;

import lombok.Builder;

@Builder
public record AlarmEnabledResponse(
        boolean alarmEnabled
) {
    public static AlarmEnabledResponse of(boolean alarmState){
        return AlarmEnabledResponse.builder()
                .alarmEnabled(alarmState)
                .build();
    }
}
