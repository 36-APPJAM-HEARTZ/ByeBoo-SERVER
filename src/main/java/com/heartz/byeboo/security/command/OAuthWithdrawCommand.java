package com.heartz.byeboo.security.command;

public record OAuthWithdrawCommand(
        Long userId,
        String code
) {
    public static OAuthWithdrawCommand of(Long userId, String code){
        return new OAuthWithdrawCommand(userId, code);
    }
}
