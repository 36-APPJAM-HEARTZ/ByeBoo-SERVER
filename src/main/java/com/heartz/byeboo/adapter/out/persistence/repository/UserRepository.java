package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.domain.type.EPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPlatformAndSerialId(EPlatform platform, String serialId);
    @Query("SELECT u.alarmEnabled FROM UserEntity u WHERE u.id = :userId")
    boolean isAlarmEnabledById(@Param("userId") Long userId);}
