package com.heartz.byeboo.constants;

import java.util.List;

public class AuthConstants {
    public static final String GRANT_TYPE = "KakaoAK ";
    public static final String TARGET_ID_TYPE = "user_id";
    public static final String CLAIM_USER_ROLE = "role";
    public static final String PREFIX_BEARER = "Bearer ";
    public static final List<String> NO_NEED_AUTH = List.of(
            "/api/health-check",
            "/api/v1/auth/login",
            "/api/v1/auth/reissue",
            "/swagger",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api-docs",
            "/api-docs/**",
            "/v3/api-docs/**",
            "/api/v1/auth/kakao/access"
    );
}
