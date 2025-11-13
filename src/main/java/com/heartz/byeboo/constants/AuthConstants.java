package com.heartz.byeboo.constants;

import java.util.List;

public class AuthConstants {

    /**
     * OAuth2 표준 상수
     */
    public static class OAuth2 {
        public static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
        public static final String TOKEN_TYPE_REFRESH = "refresh_token";
        public static final String PREFIX_BEARER = "Bearer ";
        public static final String CLAIM_USER_ROLE = "role";
        public static final String HEADER_AUTHORIZATION = "Authorization";
    }

    /**
     * 카카오 전용 상수
     */
    public static class Kakao {
        public static final String AUTHORIZATION_PREFIX = "KakaoAK ";
        public static final String TARGET_ID_TYPE = "user_id";
    }

    /**
     * 애플 전용 상수
     */
    public static class Apple {
        public static final String KEY_ID_HEADER_KEY = "kid";
        public static final String SIGN_ALGORITHM_HEADER_KEY = "alg";
        public static final String AUDIENCE = "https://appleid.apple.com";
    }

    /**
     * 공통/기타
     */
    public static final String DELETED_USER_DEFAULT_INFO = "알 수 없음";

    /**
     * 인증이 필요 없는 API 경로
     */
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
            "/api/v1/auth/kakao/access",
            "/api/v1/auth/admin/login"
    );
}
