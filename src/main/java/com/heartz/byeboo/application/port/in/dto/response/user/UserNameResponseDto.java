package com.heartz.byeboo.application.port.in.dto.response.user;

public record UserNameResponseDto(
        String name
) {
    public static UserNameResponseDto of(String name) {
        return new UserNameResponseDto(name);
    }
}
