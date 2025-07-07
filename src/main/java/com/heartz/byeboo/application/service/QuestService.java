package com.heartz.byeboo.application.service;

import com.heartz.byeboo.adapter.in.web.dto.RecordingQuestRequestDto;
import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.application.port.out.CreateQuestPort;
import com.heartz.byeboo.application.port.out.RetrieveUserPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class QuestService implements QuestUseCase {

    private final CreateQuestPort createQuestPort;
    private final RetrieveUserPort retrieveUserPort;

    @Override
    public void createRecordingQuest(RecordingQuestCreateCommand recordingQuestCreateCommand) {

        User findUser = retrieveUserPort.findById(recordingQuestCreateCommand.getUserId());

    }
}
