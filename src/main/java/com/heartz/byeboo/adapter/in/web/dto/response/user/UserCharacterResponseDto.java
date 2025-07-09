package com.heartz.byeboo.adapter.in.web.dto.response.user;

public record UserCharacterResponseDto(
        String dialogue
) {
    public static UserCharacterResponseDto of(String dialogue) {
        return new UserCharacterResponseDto(dialogue);
    }
}
