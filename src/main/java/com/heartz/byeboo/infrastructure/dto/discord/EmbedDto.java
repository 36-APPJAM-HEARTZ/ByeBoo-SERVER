package com.heartz.byeboo.infrastructure.dto.discord;

import com.heartz.byeboo.security.jwt.UserAuthentication;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;

public record EmbedDto(
        String title,
        String description
) {
    public static EmbedDto error(String fullPath, String stackTrace, Authentication authentication) {
        String userInfo = "🤷‍익명 사용자 🤷‍♀️";

        if (authentication instanceof UserAuthentication userAuth && authentication.isAuthenticated()) {
            Object principal = userAuth.getPrincipal();
            userInfo = "👶사용자 ID : " + principal;
        }

            return new EmbedDto(
                "ℹ️에러 정보",
                "### 🕖 발생 시간\n"
                        +LocalDateTime.now() + "\n"+
                        "### 👤 사용자 정보\n" + userInfo + "\n\n" +
                        "### 🔗 요청 URL\n"
                                + fullPath
                                + "\n"
                                + "### 📄 Stack Trace\n"
                                + "```\n"
                                + stackTrace
                                + "\n```"
        );
    }

    public static EmbedDto signUp(String username, Long totalMemberCount, Long userId){
        return new EmbedDto(
                "🎉 신규 회원 가입",
                "### 👤 사용자명\n" + username + "\n" +
                        "### 👩‍🌾사용자 ID\n: " +userId + "\n" +
                        "### 👥 총 회원 수\n" + totalMemberCount + "명\n" +
                        "### 🕖 가입 시간\n" + LocalDateTime.now()
        );
    }

    public static EmbedDto reportNotification(Long reportId, Long reporterId, Long targetId, String content) {

        return new EmbedDto(
                "🚨 새로운 신고 접수",
                "### 🚔 신고 ID\n" + reportId + "\n" +
                        "### 👩‍🌾 신고자 ID\n" + reporterId + "\n" +
                        "### 👤 신고 게시물 ID\n" + targetId + "\n" +
                        "### 🕖 신고 시간\n" + LocalDateTime.now() + "\n" +
                        "### 📝신고 게시물 내용\n" + content
        );
    }
}
