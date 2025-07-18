package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserJourneyEntity;
import com.heartz.byeboo.domain.type.EJourney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJourneyRepository extends JpaRepository<UserJourneyEntity, Long> {
    List<UserJourneyEntity> findAllByUserId(Long userId);
    Optional<UserJourneyEntity> findByUserIdAndJourney(Long userId, EJourney journey);
}
