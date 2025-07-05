package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
