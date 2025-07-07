package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.domain.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}
