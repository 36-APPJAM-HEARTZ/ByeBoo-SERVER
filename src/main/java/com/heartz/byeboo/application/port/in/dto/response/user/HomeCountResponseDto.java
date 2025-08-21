package com.heartz.byeboo.application.port.in.dto.response.user;

import com.heartz.byeboo.domain.type.EUserCurrentStatus;

public record HomeCountResponseDto(
        Boolean todayComplete,
        EUserCurrentStatus userCurrentStatus,
        Long count
) {
    public static HomeCountResponseDto of(Boolean todayComplete, EUserCurrentStatus userCurrentStatus, Long count) {
        return new HomeCountResponseDto(todayComplete, userCurrentStatus, count);
    }
}
