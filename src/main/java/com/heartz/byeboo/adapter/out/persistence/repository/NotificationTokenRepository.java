package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.NotificationTokenEntity;
import com.heartz.byeboo.domain.model.NotificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationTokenRepository extends JpaRepository<NotificationTokenEntity, Long> {
    Optional<NotificationTokenEntity> findByNotificationTokenAndUserId(String notificationToken, Long userId);
    void deleteByNotificationTokenAndUserId(String notificationToken, Long userId);
    List<NotificationTokenEntity> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    void deleteByTimeStampBefore(LocalDateTime threshold);
}
