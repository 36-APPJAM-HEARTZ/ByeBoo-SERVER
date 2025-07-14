package com.heartz.byeboo.infrastructure.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DiscordMessageDto(
        String content,
        List<EmbedDto> embeds
) {
}
