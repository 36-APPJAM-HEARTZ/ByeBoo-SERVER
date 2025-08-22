package com.heartz.byeboo.application.command.auth;

public record ReissueCommand(
        String refreshToken
) {
    public static ReissueCommand of(String refreshToken) {
        return new ReissueCommand(refreshToken);
    }
}
