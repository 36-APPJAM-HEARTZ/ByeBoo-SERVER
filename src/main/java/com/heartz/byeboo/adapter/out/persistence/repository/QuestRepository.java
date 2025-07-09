package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.QuestEntity;
import com.heartz.byeboo.domain.type.EJourney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestRepository extends JpaRepository<QuestEntity, Long> {
    Optional<QuestEntity> findByJourneyAndQuestNumber(EJourney journey, Long questNumber);
    List<QuestEntity> findAllByJourneyOrderByQuestNumber(EJourney journey);
}
