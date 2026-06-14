package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.NotificationEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.NotificationRepository;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.NotificationProjection;
import com.heartz.byeboo.application.port.out.notification.CreateNotificationPort;
import com.heartz.byeboo.application.port.out.notification.DeleteNotificationPort;
import com.heartz.byeboo.application.port.out.notification.RetrieveNotificationPort;
import com.heartz.byeboo.application.port.out.notification.UpdateNotificationPort;
import com.heartz.byeboo.domain.model.Notification;
import com.heartz.byeboo.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements RetrieveNotificationPort, CreateNotificationPort, DeleteNotificationPort, UpdateNotificationPort {

    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationProjection> getNotificationByUserId(Long userId) {
        return notificationRepository.findAllByUserId(userId);

    }

    @Override
    public Notification findByIdAndUserId(Long userId, Long notificationId) {
        NotificationEntity notificationEntity = notificationRepository.findByUserIdAndId(userId, notificationId);
        return NotificationMapper.entityToDomain(notificationEntity);
    }

    @Override
    public void create(Notification notification) {
        NotificationEntity notificationEntity = NotificationMapper.toEntity(notification);
        notificationRepository.save(notificationEntity);
    }

    @Override
    public void deleteByCreatedDateBefore(LocalDateTime threshold) {
        notificationRepository.deleteByCreatedDateBefore(threshold);
    }

    @Override
    public void updateIsRead(Notification notification) {
        NotificationEntity notificationEntity = NotificationMapper.toEntityForUpdate(notification);
        notificationRepository.save(notificationEntity);
    }
}
