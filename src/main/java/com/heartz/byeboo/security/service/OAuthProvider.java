package com.heartz.byeboo.security.service;

import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;


public interface OAuthProvider {

    SocialInfoResponse getUserInfo(final String providerToken);
    void requestRevoke(String code, String serialId);
}
