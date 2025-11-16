package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.port.in.dto.response.SignedUrlResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.JourneyListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.UserQuestDetailResponseDto;
import com.heartz.byeboo.application.command.*;
import com.heartz.byeboo.application.command.userquest.*;

public interface UserQuestUseCase {
    void createRecordingQuest(RecordingQuestCreateCommand command);
    void createActiveQuest(ActiveQuestCreateCommand command);
    SignedUrlResponseDto getSignedUrl(SignedUrlCreateCommand command);
    UserQuestDetailResponseDto getDetailQuest(UserQuestDetailCommand command);
    JourneyListResponseDto getCompletedJourney(CompletedJourneyCommand command);
    void updateJourneyStatus(JourneyUpdateCommand command);
    void updateRecordingQuest(RecordingQuestUpdateCommand command);
    void updateActiveQuest(ActiveQuestUpdateCommand command);
}