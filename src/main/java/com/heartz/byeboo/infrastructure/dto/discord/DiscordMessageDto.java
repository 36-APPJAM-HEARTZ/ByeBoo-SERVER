package com.heartz.byeboo.infrastructure.dto.discord;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DiscordMessageDto(
        String content,
        List<EmbedDto> embeds
) {

    public static DiscordMessageDto signUp(List<EmbedDto> embeds){
        return new DiscordMessageDto(
                "# 🎉 새로운 유저가 회원가입했어요! 🎉", embeds
        );
    }

    public static DiscordMessageDto error(List<EmbedDto> embeds){
        return new DiscordMessageDto(
                "# 🚨 에러 발생 비이이사아앙 🚨", embeds
        );
    }

    public static DiscordMessageDto report(List<EmbedDto> embeds){
        return new DiscordMessageDto(
                "# 🚨🚔👮‍♂️ 게시물 신고 접수 🚔👮🚨‍ ", embeds
        );
    }
}

