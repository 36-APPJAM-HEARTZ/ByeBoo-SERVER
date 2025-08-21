package com.heartz.byeboo.infrastructure.api.kakao;

import com.heartz.byeboo.infrastructure.dto.kakao.KakaoUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "kakaoClient",
        url = "https://kapi.kakao.com")
public interface KakaoFeignClient {

    @GetMapping("/v2/user/me")
    KakaoUserInfo getUserInfo(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken
    );

    @PostMapping("/v1/user/unlink")
    void unlinkKakaoServer(
            @RequestHeader("Authorization") String adminKey,
            @RequestParam(name = "target_id_type") String targetIdType,
            @RequestParam("target_id") Long targetId
    );

}
