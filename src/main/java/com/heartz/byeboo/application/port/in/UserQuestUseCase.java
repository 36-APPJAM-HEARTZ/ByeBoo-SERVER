package com.heartz.byeboo.application.port.in;

import com.heartz.byeboo.adapter.in.web.dto.SignedUrlResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.JourneyListResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.QuestDetailResponseDto;
import com.heartz.byeboo.application.command.*;

public interface UserQuestUseCase {
    void createRecordingQuest(RecordingQuestCreateCommand command);
    void createActiveQuest(ActiveQuestCreateCommand command);
    SignedUrlResponseDto getSignedUrl(SignedUrlCreateCommand command);
    QuestDetailResponseDto getDetailQuest(QuestDetailCommand command);
    JourneyListResponseDto getCompletedJourney(CompletedJourneyCommand command);
    void updateJourneyStatus(JourneyUpdateCommand command);
}