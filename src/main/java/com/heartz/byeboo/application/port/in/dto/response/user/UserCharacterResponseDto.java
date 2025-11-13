package com.heartz.byeboo.application.port.in.dto.response.user;

public record UserCharacterResponseDto(
        String dialogue,
        String tapDialogue
) {
    public static UserCharacterResponseDto of(String dialogue, String tapDialogue) {
        return new UserCharacterResponseDto(dialogue, tapDialogue);
    }
}
