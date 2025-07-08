package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.domain.model.UserQuest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQuestRepository extends JpaRepository<UserQuestEntity, Long> {
    Optional<UserQuestEntity> getByUserId(Long userId);
}
