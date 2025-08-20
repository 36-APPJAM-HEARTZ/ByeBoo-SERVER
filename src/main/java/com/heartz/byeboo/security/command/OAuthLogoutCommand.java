package com.heartz.byeboo.security.command;

public record OAuthLogoutCommand(
        Long userId
) {
    public static OAuthLogoutCommand from(Long userId){
        return new OAuthLogoutCommand(userId);
    }
}
