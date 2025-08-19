package com.heartz.byeboo.infrastructure.api;

import com.heartz.byeboo.infrastructure.dto.kakao.KakaoUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "kakaoClient",
        url = "https://kapi.kakao.com")
public interface KakaoFeignClient {
    @GetMapping("/v2/user/me")
    KakaoUserInfo getUserInfo(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken
    );

}
