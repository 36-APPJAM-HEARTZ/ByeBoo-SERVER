package com.heartz.byeboo.infrastructure.api.apple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.AuthErrorCode;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

@Component
public class AppleIdentityTokenParser {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected Map<String, String> parseHeaders(final String identityToken) {
        try {
            String encodedHeader = identityToken.split("\\.")[0];
            String decodedHeader = new String(Base64.getUrlDecoder().decode(encodedHeader));
            return OBJECT_MAPPER.readValue(decodedHeader, new TypeReference<>() {
            });
        } catch (JsonProcessingException | ArrayIndexOutOfBoundsException e) {
            throw new CustomException(AuthErrorCode.INVALID_APPLE_IDENTITY_TOKEN);
        }
    }

    protected Claims parsePublicKeyAndGetClaims(final String identityToken, final PublicKey publicKey) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(identityToken)
                    .getBody();

        } catch (ExpiredJwtException e) {
            throw new CustomException(AuthErrorCode.EXPIRED_IDENTITY_TOKEN);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new CustomException(AuthErrorCode.INVALID_IDENTITY_TOKEN_VALUE);
        }
    }
}
