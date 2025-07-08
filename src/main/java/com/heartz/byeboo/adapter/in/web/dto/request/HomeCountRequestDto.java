package com.heartz.byeboo.adapter.in.web.dto.request;

public record HomeCountRequestDto(
        Long id
) {
    public static HomeCountRequestDto of(Long id) {
        return new HomeCountRequestDto(id);
    }
}
