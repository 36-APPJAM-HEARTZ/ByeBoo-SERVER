package com.heartz.byeboo.application.service.auth;

import com.heartz.byeboo.application.command.auth.ProviderUserInfoCommand;
import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;


public interface OAuthProvider {

    SocialInfoResponse getUserInfo(ProviderUserInfoCommand command);
    void requestRevoke(String refreshToken, String serialId);
}
