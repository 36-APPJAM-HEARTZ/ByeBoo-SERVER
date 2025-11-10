package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.NotificationTokenEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.NotificationTokenRepository;
import com.heartz.byeboo.application.port.out.notificationtoken.CreateNotificationTokenPort;
import com.heartz.byeboo.application.port.out.notificationtoken.DeleteNotificationTokenPort;
import com.heartz.byeboo.application.port.out.notificationtoken.RetrieveNotificationTokenPort;
import com.heartz.byeboo.application.port.out.notificationtoken.UpdateNotificationTokenPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.NotificationTokenErrorCode;
import com.heartz.byeboo.domain.model.NotificationToken;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.mapper.NotificationTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FCMTokenPersistenceAdapter implements CreateNotificationTokenPort, RetrieveNotificationTokenPort, UpdateNotificationTokenPort, DeleteNotificationTokenPort {

    private final NotificationTokenRepository notificationTokenRepository;

    @Override
    public void createNotificationToken(NotificationToken notificationToken) {
        NotificationTokenEntity notificationTokenEntity = NotificationTokenMapper.toEntity(notificationToken);
        notificationTokenRepository.save(notificationTokenEntity);
    }

    @Override
    public NotificationToken getNotificationTokenByToken(String notificationToken, User user) {
        NotificationTokenEntity notificationTokenEntity = notificationTokenRepository.findByNotificationTokenAndUserId(notificationToken, user.getId())
                .orElseThrow(() -> new CustomException(NotificationTokenErrorCode.NOTIFICATION_TOKEN_NOT_FOUND));

        return NotificationTokenMapper.toDomain(notificationTokenEntity, user);
    }

    @Override
    public List<NotificationTokenEntity> findAllByUserId(Long userId) {
        return notificationTokenRepository.findAllByUserId(userId);
    }

    @Override
    public void updateTimeStamp(NotificationToken notificationToken, User user) {
        NotificationTokenEntity notificationTokenEntity = NotificationTokenMapper.toEntityForUpdate(notificationToken, user);
        notificationTokenRepository.save(notificationTokenEntity);
    }

    @Override
    public void deleteNotificationToken(String notificationToken, Long userId) {
        notificationTokenRepository.deleteByNotificationTokenAndUserId(notificationToken, userId);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        notificationTokenRepository.deleteAllByUserId(userId);
    }

    @Override
    public void deleteByConnectedAtBefore(LocalDateTime threshold) {
        notificationTokenRepository.deleteByTimeStampBefore(threshold);
    }
}
