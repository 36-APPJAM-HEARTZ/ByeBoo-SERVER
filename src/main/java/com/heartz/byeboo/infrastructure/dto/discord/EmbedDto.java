package com.heartz.byeboo.infrastructure.dto.discord;

import java.time.LocalDateTime;

public record EmbedDto(
        String title,
        String description
) {
    public static EmbedDto error(String fullPath, String stackTrace) {
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

    public static EmbedDto signUp(String username, Long totalMemberCount){
        return new EmbedDto(
                "🎉 신규 회원 가입",
                "### 👤 사용자명\n" + username + "\n" +
                        "### 👥 총 회원 수\n" + totalMemberCount + "명\n" +
                        "### 🕖 가입 시간\n" + LocalDateTime.now()
        );
    }
}
