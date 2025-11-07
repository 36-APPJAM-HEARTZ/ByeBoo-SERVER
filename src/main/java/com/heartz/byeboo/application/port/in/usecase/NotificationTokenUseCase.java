package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenDeleteCommand;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenSaveCommand;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenUpdateCommand;

public interface NotificationTokenUseCase {
    Void saveToken(NotificationTokenSaveCommand command);
    Void updateTimeStamp(NotificationTokenUpdateCommand command);
    Void deleteToken(NotificationTokenDeleteCommand command);
}
