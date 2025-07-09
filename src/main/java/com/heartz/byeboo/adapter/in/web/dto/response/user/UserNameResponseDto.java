package com.heartz.byeboo.adapter.in.web.dto.response.user;

public record UserNameResponseDto(
        String name
) {
    public static UserNameResponseDto of(String name) {
        return new UserNameResponseDto(name);
    }
}
