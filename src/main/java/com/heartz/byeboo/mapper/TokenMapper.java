package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.TokenRedisEntity;
import com.heartz.byeboo.domain.model.Token;

public class TokenMapper {
    public static Token toDomain(TokenRedisEntity tokenRedisEntity) {
        return Token.of(
                tokenRedisEntity.getId(),
                tokenRedisEntity.getRefreshToken()
        );
    }

    public static TokenRedisEntity toEntity(Token token) {
        return TokenRedisEntity.create(
                token.getId(),
                token.getRefreshToken()
        );
    }
}
