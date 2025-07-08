package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuestRepository extends JpaRepository<UserQuestEntity, Long> {
    List<UserQuestEntity> findAllByUserIdAndQuestIdOrderByCreatedDateDesc(Long userId, Long questId);
}
