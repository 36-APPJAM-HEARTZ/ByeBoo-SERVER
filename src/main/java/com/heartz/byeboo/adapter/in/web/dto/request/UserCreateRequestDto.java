package com.heartz.byeboo.adapter.in.web.dto.request;

public record UserCreateRequestDto(
        String name,
        String questStyle,
        String feeling
) {
}
