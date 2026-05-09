package com.heartz.byeboo.application.service.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {
    private final NotificationService notificationService;

    @Scheduled(cron = "0 0 3 * * *") //매일 새벽 3시 30일 지난 알림 삭제
    @Transactional
    public void deleteOldNotifications() {
        notificationService.cleanUpOldNotification();
    }
}
