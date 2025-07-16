package com.heartz.byeboo.application.port.in.dto.response.user;

public record UserCreateResponseDto(
        Long id,
        String name
) {
    public static UserCreateResponseDto of(Long id, String name) {
        return new UserCreateResponseDto(id,name);
    }
}
