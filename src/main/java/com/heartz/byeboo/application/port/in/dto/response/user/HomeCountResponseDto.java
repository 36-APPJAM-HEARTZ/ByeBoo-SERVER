package com.heartz.byeboo.application.port.in.dto.response.user;

public record HomeCountResponseDto(
        Boolean todayComplete,
        Long count
) {
    public static HomeCountResponseDto of(Boolean todayComplete, Long count) {
        return new HomeCountResponseDto(todayComplete, count);
    }
}
