package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.user.*;
import com.heartz.byeboo.application.command.userquest.CompletedCountCommand;
import com.heartz.byeboo.application.port.in.dto.response.user.*;

public interface UserUseCase {
    UserCreateResponseDto createUser(UserCreateCommand userCreateCommand);
    UserNameResponseDto getUserName(UserNameCommand userNameCommand);
    UserJourneyResponseDto getUserJourney(UserJourneyCommand userJourneyCommand);
    HomeCountResponseDto getCompletedCount(CompletedCountCommand completedCountCommand);
    Void updateInitialUserJourney(UserJourneyUpdateCommand userJourneyUpdateCommand);
    UserCharacterResponseDto getCharacterDialogue(UserCharacterDialogueCommand userCharacterDialogueCommand);
}
