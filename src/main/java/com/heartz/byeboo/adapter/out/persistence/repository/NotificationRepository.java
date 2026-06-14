package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.NotificationEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.NotificationProjection;
import com.heartz.byeboo.domain.type.ENotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    @Query("SELECT n.id AS notificationId, n.notificationType AS type, u.name AS senderNickname, n.targetId as targetId, n.landingUrl AS landingUrl, n.isRead AS isRead, n.createdDate as createdAt " +
            "FROM NotificationEntity n JOIN UserEntity u ON n.senderUserId = u.id " +
            "WHERE n.userId = :userId " +
            "order by n.createdDate desc")
    List<NotificationProjection> findAllByUserId(Long userId);
    void deleteByCreatedDateBefore(LocalDateTime threshold);
    NotificationEntity findByUserIdAndId(Long userId, Long id);

    @Modifying
    @Query("UPDATE NotificationEntity n SET n.isRead = true WHERE n.userId = :userId AND n.isRead = false")
    void updateAllIsReadByUserId(Long userId);

    boolean existsByUserIdAndIsReadFalse(Long userId);
}