package com.heartz.byeboo.infrastructure.dto.discord;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DiscordMessageDto(
        String content,
        List<EmbedDto> embeds
) {
    public static DiscordMessageDto of(List<EmbedDto> embeds){
        return new DiscordMessageDto(
                "# 🚨 에러 발생 비이이사아앙 🚨", embeds
        );
    }
}

