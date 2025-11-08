package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.domain.model.UserQuest;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

public interface UserQuestRepository extends JpaRepository<UserQuestEntity, Long> {
    List<UserQuestEntity> findAllByUserIdAndQuestIdOrderByCreatedDateDesc(Long userId, Long questId);
    Optional<UserQuestEntity> findByUserIdAndQuestId(Long userId, Long questId);
    void deleteAllByUserId(Long userId);

    @Query("SELECT q FROM UserQuestEntity q WHERE q.notified = false AND q.createdDate <= :threshold")
    List<UserQuestEntity> findUnnotifiedQuestsBefore(@Param("threshold") LocalDateTime threshold);


}
