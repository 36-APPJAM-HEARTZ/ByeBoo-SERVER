package com.heartz.byeboo.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 14)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenRedisEntity {
    @Id
    private Long id;

    private String refreshToken;

    @Builder
    public TokenRedisEntity(Long id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }

    public static TokenRedisEntity create(Long id, String refreshToken) {
        return TokenRedisEntity.builder()
                .id(id)
                .refreshToken(refreshToken)
                .build();
    }
}
