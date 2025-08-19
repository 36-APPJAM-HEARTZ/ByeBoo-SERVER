package com.heartz.byeboo.infrastructure.api;

import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;
import com.heartz.byeboo.infrastructure.dto.kakao.KakaoUserInfo;
import com.heartz.byeboo.security.service.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.heartz.byeboo.infrastructure.api.KakaoAccessToken.createKakaoAccessToken;

@Component
@RequiredArgsConstructor
public class KakaoOAuthProvider implements OAuthProvider
{
    private final KakaoFeignClient kakaoFeignClient;

    @Override
    public SocialInfoResponse getUserInfo(String providerToken) {
        KakaoAccessToken kakaoAccessToken = createKakaoAccessToken(providerToken);
        String accessTokenWithTokenType = kakaoAccessToken.getAccessTokenWithTokenType();
        KakaoUserInfo kakaoUserInfo = kakaoFeignClient.getUserInfo(accessTokenWithTokenType);
        return SocialInfoResponse.of(
                kakaoUserInfo.id().toString()
        );
    }

}
