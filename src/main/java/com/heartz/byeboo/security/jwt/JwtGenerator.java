package com.heartz.byeboo.security.jwt;

import com.heartz.byeboo.constants.AuthConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtGenerator {

    private final JwtKeyProvider jwtKeyProvider;

    @Value("${jwt.access-token.expiration}")
    @Getter
    private Integer accessExpiration;

    @Value("${jwt.refresh-token.expiration}")
    @Getter
    private Integer refreshExpiration;

    public String generateToken(Long userId, String role, boolean isAccessToken) {
        final Date now = generateNowDate();
        final Date expiration = generateExpirationDate(isAccessToken, now);

        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        if (isAccessToken) {
            claims.put(AuthConstants.OAuth2.CLAIM_USER_ROLE, role);
        }

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(jwtKeyProvider.getHmacKey())
                .compact();
    }

    public JwtParser getJwtParser() {
        return Jwts.parserBuilder()
                .setSigningKey(jwtKeyProvider.getHmacKey())
                .build();
    }

    private Date generateNowDate() {
        return new Date();
    }

    private Date generateExpirationDate(boolean isAccessToken, Date now) {
        return new Date(now.getTime() + calculateExpirationTime(isAccessToken));
    }

    private long calculateExpirationTime(boolean isAccessToken) {
        if (isAccessToken) {
            return accessExpiration;
        }
        return refreshExpiration;
    }
}
