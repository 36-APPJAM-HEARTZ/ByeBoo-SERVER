package com.heartz.byeboo.application.command.auth;

public record OAuthWithdrawCommand(
        Long userId
) {
    public static OAuthWithdrawCommand of(Long userId){
        return new OAuthWithdrawCommand(userId);
    }
}
