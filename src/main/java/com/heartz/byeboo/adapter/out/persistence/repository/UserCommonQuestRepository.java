package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommonQuestRepository extends JpaRepository<UserCommonQuestEntity, Long> {
}
