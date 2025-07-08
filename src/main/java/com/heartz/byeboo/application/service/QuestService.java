package com.heartz.byeboo.application.service;

import com.heartz.byeboo.adapter.in.web.dto.SignedUrlResponseDto;
import com.heartz.byeboo.application.command.ActiveQuestCreateCommand;
import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;
import com.heartz.byeboo.application.command.SignedUrlCreateCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.application.port.out.*;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.mapper.UserQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestService implements QuestUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveQuestPort retrieveQuestPort;
    private final CreateUserQuestPort createUserQuestPort;
    private final UpdateUserPort updateUserPort;
    private final CreateGcsPort createGcsPort;
    private final RetrieveGcsPort retrieveGcsPort;

    @Override
    @Transactional
    public void createRecordingQuest(RecordingQuestCreateCommand command) {

        User findUser = retrieveUserPort.getUserById(command.getUserId());
        validateUserQuest(findUser, command.getQuestId());

        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());
        UserQuest userQuest = UserQuestMapper.commandToDomainRecording(command, findUser, findQuest);
        createUserQuestPort.createUserQuest(userQuest);
        findUser.updateCurrentNumber();
        updateUserPort.updateCurrentNumber(findUser.getId());
    }

    @Override
    @Transactional
    public void createActiveQuest(ActiveQuestCreateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        validateUserQuest(findUser, command.getQuestId());
        validateObjectExist(command.getImageKey().toString());
        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());
        UserQuest userQuest = UserQuestMapper.commandToDomainActive(command, findUser, findQuest);
        createUserQuestPort.createUserQuest(userQuest);
        findUser.updateCurrentNumber();
        updateUserPort.updateCurrentNumber(findUser.getId());
    }

    @Override
    public SignedUrlResponseDto getSignedUrl(SignedUrlCreateCommand command) {
        retrieveUserPort.getUserById(command.getUserId());
        String signedUrl = createGcsPort.createSignedUrl(command.getImageKey(), command.getContentType());
        return SignedUrlResponseDto.of(signedUrl);
    }

    private void validateUserQuest(User user, Long questId){
        if (!user.getCurrentNumber().equals(questId)) {
            throw new CustomException(QuestErrorCode.INVALID_QUEST_PROGRESS);
        }

        if (user.getCurrentNumber() >= 30){
            throw new CustomException(QuestErrorCode.CURRENT_NUMBER_OVER_MAX);
        }
    }

    private void validateObjectExist(String imageKey){
        if (!retrieveGcsPort.isObjectExists(imageKey)){
            throw new CustomException(QuestErrorCode.IMAGE_NOT_UPLOADED);
        }
    }
}
