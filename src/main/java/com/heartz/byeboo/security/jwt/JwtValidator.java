package com.heartz.byeboo.security.jwt;

import com.heartz.byeboo.constants.AuthConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.AuthErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final JwtGenerator jwtGenerator;

    public void validateAccessToken(final String accessToken) {
        try {
            String role = parseToken(accessToken).get(AuthConstants.OAuth2.CLAIM_USER_ROLE, String.class);
            if (role == null) {
                throw new CustomException(AuthErrorCode.INVALID_ACCESS_TOKEN_VALUE);
            }
        } catch (ExpiredJwtException e) {
            throw new CustomException(AuthErrorCode.EXPIRED_ACCESS_TOKEN);
        } catch (Exception e) {
            throw new CustomException(AuthErrorCode.INVALID_ACCESS_TOKEN_VALUE);
        }
    }

    public Long validateRefreshToken(final String refreshToken) {
        try {
            Claims claims = parseToken(getToken(refreshToken));
            return Long.valueOf(claims.getSubject());
        } catch (ExpiredJwtException e) {
            throw new CustomException(AuthErrorCode.EXPIRED_REFRESH_TOKEN);
        } catch (Exception e) {
            throw new CustomException(AuthErrorCode.INVALID_REFRESH_TOKEN_VALUE);
        }
    }

    //리프레시 토큰 일치 여부
    public void checkTokenEquality(final String refreshToken, final String storedRefreshToken) {
        if (!getToken(refreshToken).equals(storedRefreshToken)) {
            throw new CustomException(AuthErrorCode.MISMATCH_REFRESH_TOKEN);
        }
    }

    private String getToken(final String refreshToken) {
        if (refreshToken.startsWith(AuthConstants.OAuth2.PREFIX_BEARER)) {
            return refreshToken.substring(AuthConstants.OAuth2.PREFIX_BEARER.length());
        }
        throw new CustomException(AuthErrorCode.MISSING_BEARER_PREFIX);
    }

    private Claims parseToken(final String token) {
        return jwtGenerator.getJwtParser().parseClaimsJws(token).getBody();
    }
}
