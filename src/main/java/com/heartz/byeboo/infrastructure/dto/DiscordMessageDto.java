package com.heartz.byeboo.infrastructure.dto;

import lombok.Builder;

import java.util.List;

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

