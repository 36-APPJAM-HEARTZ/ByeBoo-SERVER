package com.heartz.byeboo.application.port.in;

import com.heartz.byeboo.application.command.ActiveQuestCreateCommand;
import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;

public interface QuestUseCase {
    void createRecordingQuest(RecordingQuestCreateCommand command);
    void createActiveQuest(ActiveQuestCreateCommand command);
}