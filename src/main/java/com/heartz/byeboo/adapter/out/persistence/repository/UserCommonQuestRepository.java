package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface UserCommonQuestRepository extends JpaRepository<UserCommonQuestEntity, Long> {
    boolean existsByUserIdAndCreatedDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
