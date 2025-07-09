package com.heartz.byeboo.application.port.in;

import com.heartz.byeboo.adapter.in.web.dto.response.SignedUrlResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.userquest.JourneyListResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.userquest.UserQuestDetailResponseDto;
import com.heartz.byeboo.application.command.*;
import com.heartz.byeboo.application.command.userquest.*;

public interface UserQuestUseCase {
    void createRecordingQuest(RecordingQuestCreateCommand command);
    void createActiveQuest(ActiveQuestCreateCommand command);
    SignedUrlResponseDto getSignedUrl(SignedUrlCreateCommand command);
    UserQuestDetailResponseDto getDetailQuest(QuestDetailCommand command);
    JourneyListResponseDto getCompletedJourney(CompletedJourneyCommand command);
    void updateJourneyStatus(JourneyUpdateCommand command);
}