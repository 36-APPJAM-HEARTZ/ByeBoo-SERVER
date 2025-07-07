package com.heartz.byeboo.application.service;

import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.application.port.out.CreateUserQuestPort;
import com.heartz.byeboo.application.port.out.RetrieveQuestPort;
import com.heartz.byeboo.application.port.out.RetrieveUserPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.mapper.UserQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestService implements QuestUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveQuestPort retrieveQuestPort;
    private final CreateUserQuestPort createUserQuestPort;

    @Override
    public void createRecordingQuest(RecordingQuestCreateCommand recordingQuestCreateCommand) {

        User findUser = retrieveUserPort.findById(recordingQuestCreateCommand.getUserId());
        validateQuest(findUser, recordingQuestCreateCommand);

        Quest findQuest = retrieveQuestPort.findById(recordingQuestCreateCommand.getQuestId());
        UserQuest userQuest = UserQuestMapper.commandToDomain(recordingQuestCreateCommand, findUser, findQuest);
        createUserQuestPort.createUserQuest(userQuest);

    }

    private void validateQuest(User user, RecordingQuestCreateCommand command){
        if (!user.getCurrentNumber().equals(command.getQuestId())) {
            throw new CustomException(QuestErrorCode.INVALID_QUEST_PROGRESS);
        }
    }
}
