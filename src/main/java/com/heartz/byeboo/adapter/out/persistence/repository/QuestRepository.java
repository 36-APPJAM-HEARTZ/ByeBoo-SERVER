package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.QuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<QuestEntity, Long> {
}
