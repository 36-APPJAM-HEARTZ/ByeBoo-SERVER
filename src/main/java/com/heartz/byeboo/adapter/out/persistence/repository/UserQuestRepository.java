package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestRepository extends JpaRepository<UserQuestEntity, Long> {
}
