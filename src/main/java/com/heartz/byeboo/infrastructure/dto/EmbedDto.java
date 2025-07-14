package com.heartz.byeboo.infrastructure.dto;

import java.time.LocalDateTime;

public record EmbedDto(
        String title,
        String description
) {
    public static EmbedDto of(String fullPath, String stackTrace){
        return new EmbedDto(
                "ℹ️에러 정보",
                "### 🕖 발생 시간\n"
                        +LocalDateTime.now() + "\n"+
                "### 🔗 요청 URL\n"
                        + fullPath
                        + "\n"
                        + "### 📄 Stack Trace\n"
                        + "```\n"
                        + stackTrace
                        + "\n```"

        );
    }
}
