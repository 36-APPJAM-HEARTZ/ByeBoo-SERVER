package com.heartz.byeboo.application.service.userquest;

import com.heartz.byeboo.adapter.out.FCMNotificationPersistenceAdapter;
import com.heartz.byeboo.adapter.out.persistence.entity.NotificationTokenEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserIdCurrentNumberProjection;
import com.heartz.byeboo.application.command.SignedUrlCreateCommand;
import com.heartz.byeboo.application.command.userquest.*;
import com.heartz.byeboo.application.port.in.dto.response.SignedUrlResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.JourneyListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.JourneyResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.UserQuestDetailResponseDto;
import com.heartz.byeboo.application.port.in.usecase.UserQuestUseCase;
import com.heartz.byeboo.application.port.out.gcs.CreateObjectPort;
import com.heartz.byeboo.application.port.out.gcs.DeleteObjectPort;
import com.heartz.byeboo.application.port.out.gcs.RetrieveObjectPort;
import com.heartz.byeboo.application.port.out.gcs.ValidateObjectPort;
import com.heartz.byeboo.application.port.out.notificationtoken.RetrieveNotificationTokenPort;
import com.heartz.byeboo.application.port.out.quest.RetrieveQuestPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserJourneyPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.user.UpdateUserJourneyPort;
import com.heartz.byeboo.application.port.out.user.UpdateUserPort;
import com.heartz.byeboo.application.port.out.userquest.CreateUserQuestPort;
import com.heartz.byeboo.application.port.out.userquest.RetrieveUserQuestPort;
import com.heartz.byeboo.application.port.out.userquest.UpdateUserQuestPort;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.heartz.byeboo.constants.QuestConstants.QUEST_COUNT_MAX;

@Service
@RequiredArgsConstructor
@Slf4j
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
    private final RetrieveNotificationTokenPort retrieveNotificationTokenPort;
    private final FCMNotificationPersistenceAdapter fCMNotificationPersistenceAdapter;
    private final UpdateUserQuestPort updateUserQuestPort;
    private final DeleteObjectPort deleteObjectPort;

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
        findUser.startNewJourney();
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

    @Transactional(readOnly = true)
    public void sendQuestNotifications() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thresholdEnd = now.minusHours(24);
        LocalDateTime thresholdStart = thresholdEnd.minusMinutes(1);

        log.info("[Scheduler] Quest 알림 스케줄러 실행됨 - now={}, start={}, end={}", now, thresholdStart, thresholdEnd);

        List<UserIdCurrentNumberProjection> userIdCurrentNumberProjections = retrieveUserPort.findUsersWithExpiredQuest(thresholdStart, thresholdEnd);
        log.info("[Scheduler] 만료된 퀘스트 유저 수: {}", userIdCurrentNumberProjections.size());

        for (UserIdCurrentNumberProjection projection: userIdCurrentNumberProjections) {
            log.info("[Scheduler] 유저 처리: id={}, currentNumber={}",projection.getId(), projection.getCurrentNumber());
            if(projection.getCurrentNumber() != 31) {
                List<NotificationTokenEntity> tokens = retrieveNotificationTokenPort.findAllByUserId(projection.getId());

                for (NotificationTokenEntity token : tokens) {
                    try {
                        fCMNotificationPersistenceAdapter.sendMessage(token.getNotificationToken());
                    } catch (Exception e) {
                        log.info("FCM 전송 실패: token={}", token.getNotificationToken(), e);
                    }
                }
            }
        }
    }

    @Override
    @Transactional
    public void updateRecordingQuest(RecordingQuestCreateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());
        UserQuest userQuest = retrieveUserQuestPort.getUserQuestByUserAndQuest(findUser, findQuest);

        userQuest.updateAnswer(command.getAnswer());
        userQuest.updateEQuestEmotionState(command.getQuestEmotionState());

        updateUserQuestPort.updateUserQuest(userQuest);

        isUserJourneyCompleted(findUser);
    }

    @Override
    @Transactional
    public void updateActiveQuest(ActiveQuestCreateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());
        UserQuest userQuest = retrieveUserQuestPort.getUserQuestByUserAndQuest(findUser, findQuest);
        String imageKeyToDelete = userQuest.getImageKey().toString();

        validateObjectExist(command.getImageKey().toString());

        userQuest.updateImageKey(command.getImageKey());
        userQuest.updateAnswer(command.getAnswer());
        userQuest.updateEQuestEmotionState(command.getQuestEmotionState());

        if(!command.getImageKey().toString().equals(imageKeyToDelete))
            deleteObjectPort.deleteObject(imageKeyToDelete);

        updateUserQuestPort.updateUserQuest(userQuest);
    }
}
