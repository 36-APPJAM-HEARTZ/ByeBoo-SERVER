package com.heartz.byeboo.security.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@Getter
public class JwtKeyProvider implements InitializingBean {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key hmacKey;

    @Override public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.hmacKey = Keys.hmacShaKeyFor(keyBytes);
    }
}
