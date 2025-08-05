package com.heartz.byeboo.application.port.in.dto.response.user;

public record UserCharacterResponseDto(
        String dialogue
) {
    public static UserCharacterResponseDto of(String dialogue) {
        return new UserCharacterResponseDto(dialogue);
    }
}
