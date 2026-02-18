package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserCommonQuestRepository extends JpaRepository<UserCommonQuestEntity, Long> {
    boolean existsByUserIdAndCreatedDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
    long deleteByUserIdAndId(Long userId, Long id);
    Optional<UserCommonQuestEntity> findByUserIdAndId(Long userId, Long id);
}
