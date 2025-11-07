package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.user.*;
import com.heartz.byeboo.application.command.userquest.CompletedCountCommand;
import com.heartz.byeboo.application.port.in.dto.response.user.*;

public interface UserUseCase {
    UserCreateResponseDto updateUser(UserCreateCommand userCreateCommand);
    UserNameResponseDto getUserName(UserNameCommand userNameCommand);
    UserNameResponseDto updateUserName(UserNameUpdateCommand userNameUpdateCommand);
    UserJourneyResponseDto getUserJourney(UserJourneyCommand userJourneyCommand);
    HomeCountResponseDto getCompletedCount(CompletedCountCommand completedCountCommand);
    Void updateInitialUserJourney(UserJourneyUpdateCommand userJourneyUpdateCommand);
    UserCharacterResponseDto getCharacterDialogue(UserCharacterDialogueCommand userCharacterDialogueCommand);
    AlarmEnabledResponse updateAlarmPermission(AlarmPermissionCommand alarmPermissionCommand);
}
