package com.heartz.byeboo.application.service.userquest;

import com.heartz.byeboo.adapter.out.FCMNotificationPersistenceAdapter;
import com.heartz.byeboo.adapter.out.FCMTokenPersistenceAdapter;
import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.NotificationTokenRepository;
import com.heartz.byeboo.adapter.out.persistence.repository.UserQuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestNotificationScheduler {
    private final UserQuestRepository userQuestRepository;
    private final NotificationTokenRepository notificationTokenRepository;
    private final FCMNotificationPersistenceAdapter fCMNotificationPersistenceAdapter;

    @Scheduled(cron = "0 */1 * * * *", zone = "Asia/Seoul") // 매 1분마다 실행
    @Transactional
    public void sendQuestNotifications() {
        LocalDateTime threshold = LocalDateTime.now().minusHours(24);
        List<UserQuestEntity> quests = userQuestRepository.findUnnotifiedQuestsBefore(threshold);

        for (UserQuestEntity quest : quests) {
            notificationTokenRepository.findByUserId(quest.getUserId()).ifPresent(token -> {
                fCMNotificationPersistenceAdapter.sendMessage(
                        token.getFcmToken()
                );
                quest.updateNotified(true);
            });
        }
    }


}
