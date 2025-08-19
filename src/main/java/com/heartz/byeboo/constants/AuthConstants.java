package com.heartz.byeboo.constants;

import java.util.List;

public class AuthConstants {
    public static String CLAIM_USER_ID = "uuid";
    public static String CLAIM_USER_ROLE = "role";
    public static String PREFIX_BEARER = "Bearer ";
    public static List<String> NO_NEED_AUTH = List.of(
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
