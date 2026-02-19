package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.CommonQuestEntity;
import com.heartz.byeboo.domain.model.CommonQuest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CommonQuestRepository extends JpaRepository<CommonQuestEntity, Long> {
    Optional<CommonQuestEntity> findByTargetDate(LocalDate targetDate);
}
