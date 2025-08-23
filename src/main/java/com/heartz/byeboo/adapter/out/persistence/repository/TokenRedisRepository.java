package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.TokenRedisEntity;
import org.springframework.data.repository.CrudRepository;

public interface TokenRedisRepository extends CrudRepository<TokenRedisEntity, Long> {
}
