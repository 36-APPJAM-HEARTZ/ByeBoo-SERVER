package com.heartz.byeboo.application.service.userquest;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class QuestNotificationScheduler {

    private final UserQuestService userQuestService;

    @Scheduled(cron = "0 */1 * * * *", zone = "Asia/Seoul") // 매 1분마다 실행
    @Transactional
    public void sendQuestNotifications() {
        userQuestService.sendQuestNotifications();
    }
}
