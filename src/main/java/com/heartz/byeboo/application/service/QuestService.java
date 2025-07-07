package com.heartz.byeboo.application.service;

import com.heartz.byeboo.adapter.in.web.dto.RecordingQuestRequestDto;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.application.port.out.CreateQuestPort;
import org.springframework.stereotype.Service;

@Service
public class QuestService implements QuestUseCase {

    private final CreateQuestPort createQuestPort;

    @Override
    public void createRecordingQuest(RecordingQuestRequestDto recordingQuestRequestDto) {

    }
}
