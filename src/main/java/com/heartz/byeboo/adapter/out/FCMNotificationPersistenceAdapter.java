package com.heartz.byeboo.adapter.out;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.heartz.byeboo.application.port.out.notification.SendNotificationPort;
import com.heartz.byeboo.constants.NotificationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FCMNotificationPersistenceAdapter implements SendNotificationPort{
    @Override
    public void sendMessage(String targetToken) {
        Message message = Message.builder()
                .setToken(targetToken)
                .setNotification(Notification.builder()
                        .setTitle(NotificationConstants.notificationTitle)
                        .setBody(NotificationConstants.notificationBody)
                        .build())
                .putData("destination", "questHome")
                .build();
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("FCM 발송 성공: " + response);
        } catch (FirebaseMessagingException e) {
            log.info("FCM 발송 실패: " + e.getMessage());
        }
    }
}
