package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.TokenRedisEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRedisRepository extends CrudRepository<TokenRedisEntity, Long> {
    Optional<TokenRedisEntity> findTokenRedisEntityByRefreshToken(String refreshToken);
}
