package com.heartz.byeboo.infrastructure.api;

import com.heartz.byeboo.constants.AuthConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoAccessToken {

    private String accessToken;

    protected static KakaoAccessToken createKakaoAccessToken(String accessToken) {
        return new KakaoAccessToken(accessToken);
    }

    protected String getAccessTokenWithTokenType() {
        return AuthConstants.PREFIX_BEARER + accessToken;
    }
}
