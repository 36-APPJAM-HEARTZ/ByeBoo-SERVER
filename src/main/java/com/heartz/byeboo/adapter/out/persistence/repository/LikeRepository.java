package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    void deleteByUserIdAndUserCommonQuestId(Long userId, Long userCommonQuestId);
    boolean existsByUserIdAndUserCommonQuestId(Long userId, Long userCommonQuestId);
    int countByUserCommonQuestId(Long userCommonQuestId);
}
