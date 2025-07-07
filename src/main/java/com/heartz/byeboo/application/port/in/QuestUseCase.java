package com.heartz.byeboo.application.port.in;

import com.heartz.byeboo.adapter.in.web.dto.RecordingQuestRequestDto;
import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;

public interface QuestUseCase {
    void createRecordingQuest(RecordingQuestCreateCommand command);
}