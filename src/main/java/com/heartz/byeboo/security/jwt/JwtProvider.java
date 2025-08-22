package com.heartz.byeboo.security.jwt;

import io.jsonwebtoken.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtGenerator jwtGenerator;

    public TokenResponse issueTokens(final Long userId, final String role) {
        return TokenResponse.of(generateAccessToken(userId, role), generateRefreshToken(userId, role));
    }

    private String generateAccessToken(final Long userId, final String role) {
        return jwtGenerator.generateToken(userId, role, true);
    }

    private String generateRefreshToken(final Long userId, final String role) {
        return jwtGenerator.generateToken(userId, role, false);
    }

    public Long getSubject(final String token) { //userId
        JwtParser jwtParser = jwtGenerator.getJwtParser();
        return Long.valueOf(jwtParser.parseClaimsJws(token)
                .getBody()
                .getSubject());
    }
}
