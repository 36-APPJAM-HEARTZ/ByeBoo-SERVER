package com.heartz.byeboo.adapter.in.web.dto;

public record UserCreateResponseDto(
        Long id,
        String name
) {
    public static UserCreateResponseDto of(Long id, String name) {
        return new UserCreateResponseDto(id,name);
    }
}
