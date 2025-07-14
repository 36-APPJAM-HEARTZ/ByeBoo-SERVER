package com.heartz.byeboo.infrastructure.dto;

import lombok.Builder;

@Builder
public record EmbedDto(
        String title,
        String description
) {
}
