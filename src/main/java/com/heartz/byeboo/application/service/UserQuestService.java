package com.heartz.byeboo.application.service;

import com.heartz.byeboo.adapter.in.web.dto.SignedUrlResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.JourneyListResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.JourneyResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.QuestDetailResponseDto;
import com.heartz.byeboo.application.command.*;
import com.heartz.byeboo.application.port.in.UserQuestUseCase;
import com.heartz.byeboo.application.port.out.*;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import com.heartz.byeboo.mapper.JourneyStyleMapper;
import com.heartz.byeboo.mapper.UserQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.heartz.byeboo.constants.QuestConstants.QUEST_COUNT_MAX;

@Service
@RequiredArgsConstructor
public class UserQuestService implements UserQuestUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveQuestPort retrieveQuestPort;
    private final CreateUserQuestPort createUserQuestPort;
    private final UpdateUserPort updateUserPort;
    private final CreateGcsPort createGcsPort;
    private final ValidateGcsPort validateGcsPort;
    private final RetrieveUserQuestPort retrieveUserQuestPort;
    private final RetrieveGcsPort retrieveGcsPort;
    private final RetrieveUserJourneyPort retrieveUserJourneyPort;
    private final UpdateUserJourneyPort updateUserJourneyPort;

    @Override
    @Transactional
    public void createRecordingQuest(RecordingQuestCreateCommand command) {

        User findUser = retrieveUserPort.getUserById(command.getUserId());
        validateUserQuest(findUser, command.getQuestId());

        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());
        UserQuest userQuest = UserQuestMapper.commandToDomainRecording(command, findUser, findQuest);
        createUserQuestPort.createUserQuest(userQuest);
        findUser.updateCurrentNumber();
        updateUserPort.updateCurrentNumber(findUser);

        isUserJourneyCompleted(findUser);
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
        updateUserPort.updateCurrentNumber(findUser);

        isUserJourneyCompleted(findUser);
    }

    @Override
    public SignedUrlResponseDto getSignedUrl(SignedUrlCreateCommand command) {
        retrieveUserPort.getUserById(command.getUserId());
        String signedUrl = createGcsPort.createSignedUrl(command.getImageKey(), command.getContentType());
        return SignedUrlResponseDto.of(signedUrl);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestDetailResponseDto getDetailQuest(QuestDetailCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());

        UserQuest userQuest = retrieveUserQuestPort.getUserQuestByUserAndQuest(findUser, findQuest);
        String signedUrl = retrieveGcsPort.getSignedUrl(userQuest.getImageKey().toString());

        return QuestDetailResponseDto.of(userQuest, findQuest, signedUrl);
    }

    @Override
    @Transactional(readOnly = true)
    public JourneyListResponseDto getCompletedJourney(CompletedJourneyCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        List<UserJourney> userJourneys = retrieveUserJourneyPort.getJourneysByUser(findUser);

        List<JourneyResponseDto> inCompletedJourneys = filterUserJourney(userJourneys, EJourneyStatus.NOT_COMPLETED);
        List<JourneyResponseDto> completedJourneys = filterUserJourney(userJourneys, EJourneyStatus.COMPLETED);

        return JourneyListResponseDto.of(inCompletedJourneys.size(), inCompletedJourneys, completedJourneys.size(), completedJourneys);
    }

    private void validateUserQuest(User user, Long questId){
        if (!user.getCurrentNumber().equals(questId)) {
            throw new CustomException(UserQuestErrorCode.INVALID_QUEST_PROGRESS);
        }

        if (user.getCurrentNumber() >= QUEST_COUNT_MAX){
            throw new CustomException(QuestErrorCode.CURRENT_NUMBER_OVER_MAX);
        }
    }

    private void validateObjectExist(String imageKey){
        if (!validateGcsPort.isObjectExists(imageKey)){
            throw new CustomException(UserQuestErrorCode.IMAGE_NOT_UPLOADED);
        }
    }

    //퀘스트 번호 31일때 여정 완료 상태로 변경
    private void isUserJourneyCompleted(User user){
        if (user.getCurrentNumber() == QUEST_COUNT_MAX){
            UserJourney ongoingUserJourney = retrieveUserJourneyPort.getOngoingUserJourneyByUser(user);
            ongoingUserJourney.updateUserJourneyCompleted();
            updateUserJourneyPort.updateUserJourneyCompleted(ongoingUserJourney);
        }
    }

    //journey 완료 여부에 따라 필터링
    private List<JourneyResponseDto> filterUserJourney(List<UserJourney> userJourneys, EJourneyStatus journeyStatus){
        return userJourneys.stream().filter(
                userJourney -> userJourney.getJourneyStatus() == journeyStatus
        ).map(
                userJourney -> JourneyResponseDto.from(userJourney.getJourney(), JourneyStyleMapper.journeyToQuestStyle(userJourney.getJourney()))
        ).toList();
    }
}
