package com.heartz.byeboo.application.service.notificationtoken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationTokenScheduler {

    private final NotificationTokenService notificationTokenService;

    //매일 자정, 30일 동안 사용되지 않은 FCM 토큰 삭제
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void cleanUpOldNotificationTokens() {
        log.info("cleanUpOldNotificationTokens");
        notificationTokenService.cleanUpOldNotificationTokens();
    }
}
