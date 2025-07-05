package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserJourneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJourneyRepository extends JpaRepository<UserJourneyEntity, Long> {
}
