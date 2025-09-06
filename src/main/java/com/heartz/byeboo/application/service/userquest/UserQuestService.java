package com.heartz.byeboo.application.service.userquest;

import com.heartz.byeboo.application.port.in.dto.response.SignedUrlResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.JourneyListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.JourneyResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.UserQuestDetailResponseDto;
import com.heartz.byeboo.application.command.*;
import com.heartz.byeboo.application.command.userquest.*;
import com.heartz.byeboo.application.port.in.usecase.UserQuestUseCase;
import com.heartz.byeboo.application.port.out.*;
import com.heartz.byeboo.application.port.out.quest.RetrieveQuestPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserJourneyPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.user.UpdateUserJourneyPort;
import com.heartz.byeboo.application.port.out.user.UpdateUserPort;
import com.heartz.byeboo.application.port.out.userquest.CreateUserQuestPort;
import com.heartz.byeboo.application.port.out.userquest.RetrieveUserQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import com.heartz.byeboo.domain.exception.UserJourneyErrorCode;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import com.heartz.byeboo.domain.type.EQuestStyle;
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
    private final CreateObjectPort createGcsPort;
    private final ValidateObjectPort validateGcsPort;
    private final RetrieveUserQuestPort retrieveUserQuestPort;
    private final RetrieveObjectPort retrieveGcsPort;
    private final RetrieveUserJourneyPort retrieveUserJourneyPort;
    private final UpdateUserJourneyPort updateUserJourneyPort;

    @Override
    @Transactional
    public void createRecordingQuest(RecordingQuestCreateCommand command) {

        User findUser = retrieveUserPort.getUserById(command.getUserId());
        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());

        validateUserQuest(findUser, findQuest, EQuestStyle.RECORDING);
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
        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());

        validateUserQuest(findUser, findQuest, EQuestStyle.ACTIVE);
        validateObjectExist(command.getImageKey().toString());
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
    public UserQuestDetailResponseDto getDetailQuest(UserQuestDetailCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());

        UserQuest userQuest = retrieveUserQuestPort.getUserQuestByUserAndQuest(findUser, findQuest);

        if (findQuest.getQuestStyle() == EQuestStyle.ACTIVE) {
            String signedUrl = retrieveGcsPort.getSignedUrl(userQuest.getImageKey().toString());
            return UserQuestDetailResponseDto.of(userQuest, findQuest, signedUrl);
        }
        return UserQuestDetailResponseDto.of(userQuest, findQuest);
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

    @Override
    @Transactional
    public void updateJourneyStatus(JourneyUpdateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        UserJourney findUserJourney = retrieveUserJourneyPort.getUserJourneyByUserAndJourney(findUser, command.getJourney());

        isJourneyAlreadyStart(findUserJourney);

        findUserJourney.updateInitialUserJourney();
        findUser.updateJourney(command.getJourney());
        updateUserJourneyPort.updateUserJourney(findUserJourney);
        findUser.initializeCurrentNumber();
        updateUserPort.updateCurrentNumber(findUser);
    }

    private void validateUserQuest(User user,  Quest quest, EQuestStyle questStyle){
        if (!user.getCurrentNumber().equals(quest.getQuestNumber())) {
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
                userJourney -> JourneyResponseDto.from(
                        userJourney.getJourney(),
                        JourneyStyleMapper.journeyToQuestStyle(userJourney.getJourney())
                )
        ).toList();
    }

    //이미 완료된 journey를 시작하려할때
    private void isJourneyAlreadyStart(UserJourney userJourney){
         if (userJourney.getJourneyStatus() != EJourneyStatus.NOT_COMPLETED){
            throw new CustomException(UserJourneyErrorCode.CONFLICT_USER_JOURNEY);
        }
    }
}
