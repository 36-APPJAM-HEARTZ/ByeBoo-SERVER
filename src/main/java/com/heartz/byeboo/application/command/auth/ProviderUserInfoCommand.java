package com.heartz.byeboo.application.command.auth;

import lombok.Builder;

@Builder
public record ProviderUserInfoCommand(
        String providerToken,
        String authorizationCode
) {
    public static ProviderUserInfoCommand of(String providerToken, String authorizationCode){
        return ProviderUserInfoCommand.builder()
                .providerToken(providerToken)
                .authorizationCode(authorizationCode)
                .build();
    }
}
