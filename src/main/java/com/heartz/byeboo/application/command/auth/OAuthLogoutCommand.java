package com.heartz.byeboo.application.command.auth;

public record OAuthLogoutCommand(
        Long userId
) {
    public static OAuthLogoutCommand from(Long userId){
        return new OAuthLogoutCommand(userId);
    }
}
