package com.heartz.byeboo.application.service.notification;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.NotificationProjection;
import com.heartz.byeboo.application.port.in.dto.response.notification.NotificationListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.notification.NotificationResponseDto;
import com.heartz.byeboo.application.port.in.usecase.NotificationUseCase;
import com.heartz.byeboo.application.port.out.notification.DeleteNotificationPort;
import com.heartz.byeboo.application.port.out.notification.RetrieveNotificationPort;
import com.heartz.byeboo.domain.type.ENotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.heartz.byeboo.domain.type.ENotificationType.*;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationUseCase {

    private final RetrieveNotificationPort retrieveNotificationPort;
    private final DeleteNotificationPort deleteNotificationPort;

    @Override
    @Transactional(readOnly = true)
    public NotificationListResponseDto getListNotification(Long userId) {
        List<NotificationProjection> notificationProjections =
                retrieveNotificationPort.getNotificationByUserId(userId);

        List<NotificationResponseDto> notifications = notificationProjections.stream()
                .map(notificationProjection -> {
                    ENotificationType type = notificationProjection.getType();

                    String title = type.getTitle();

                    String content = type.createContent(
                            notificationProjection.getTargetId(),
                            notificationProjection.getSenderNickname()
                    );

                    return NotificationResponseDto.of(
                            notificationProjection.getNotificationId(),
                            content,
                            title,
                            notificationProjection.getIsRead(),
                            notificationProjection.getCreatedAt(),
                            notificationProjection.getLandingUrl(),
                            type
                    );
                })
                .toList();

        return NotificationListResponseDto.from(notifications);
    }

    @Override
    public void cleanUpOldNotification() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(30);
        deleteNotificationPort.deleteByCreatedDateBefore(threshold);
    }


}
