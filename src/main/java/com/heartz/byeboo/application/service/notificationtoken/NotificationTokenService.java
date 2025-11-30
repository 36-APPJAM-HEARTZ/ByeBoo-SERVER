package com.heartz.byeboo.application.service.notificationtoken;

import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenDeleteCommand;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenSaveCommand;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenUpdateCommand;
import com.heartz.byeboo.application.port.in.usecase.NotificationTokenUseCase;
import com.heartz.byeboo.application.port.out.notificationtoken.CreateNotificationTokenPort;
import com.heartz.byeboo.application.port.out.notificationtoken.DeleteNotificationTokenPort;
import com.heartz.byeboo.application.port.out.notificationtoken.RetrieveNotificationTokenPort;
import com.heartz.byeboo.application.port.out.notificationtoken.UpdateNotificationTokenPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.NotificationTokenErrorCode;
import com.heartz.byeboo.domain.model.NotificationToken;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.mapper.NotificationTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationTokenService implements NotificationTokenUseCase {
    private final RetrieveUserPort retrieveUserPort;
    private final CreateNotificationTokenPort createNotificationTokenPort;
    private final RetrieveNotificationTokenPort retrieveNotificationTokenPort;
    private final UpdateNotificationTokenPort updateNotificationTokenPort;
    private final DeleteNotificationTokenPort deleteNotificationTokenPort;

    @Override
    @Transactional
    public Void saveToken(NotificationTokenSaveCommand command) {
        User currentUser = retrieveUserPort.getUserById(command.userId());
        NotificationToken notificationToken = NotificationTokenMapper.commandToDomain(command, currentUser);
        isNotificationTokenExist(command.notificationToken());
        createNotificationTokenPort.createNotificationToken(notificationToken);
        return null;
    }

    @Override
    @Transactional
    public Void updateTimeStamp(NotificationTokenUpdateCommand command) {
        User currentUser = retrieveUserPort.getUserById(command.userId());
        NotificationToken notificationToken = retrieveNotificationTokenPort.getNotificationTokenByToken(command.notificationToken(), currentUser);
        notificationToken.updateTimeStamp();
        updateNotificationTokenPort.updateTimeStamp(notificationToken, currentUser);
        return null;
    }

    @Override
    @Transactional
    public Void deleteToken(NotificationTokenDeleteCommand command) {
        User currentUser = retrieveUserPort.getUserById(command.userId());
        deleteNotificationTokenPort.deleteNotificationToken(command.notificationToken(), currentUser.getId());
        return null;
    }

    @Override
    @Transactional
    public void cleanUpOldNotificationTokens() {
        LocalDateTime limitDate = LocalDateTime.now().minusDays(30);
        deleteNotificationTokenPort.deleteByConnectedAtBefore(limitDate);
    }

    void isNotificationTokenExist(String notificationToken){
        if(retrieveNotificationTokenPort.existsByNotificationToken(notificationToken)){
            throw new CustomException(NotificationTokenErrorCode.ALREADY_EXIST_TOKEN);
        }
    }
}
