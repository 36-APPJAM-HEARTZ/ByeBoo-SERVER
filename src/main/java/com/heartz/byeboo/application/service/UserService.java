package com.heartz.byeboo.application.service;

import com.heartz.byeboo.application.command.user.*;
import com.heartz.byeboo.application.command.userquest.CompletedCountCommand;
import com.heartz.byeboo.application.port.in.usecase.UserUseCase;
import com.heartz.byeboo.application.port.in.dto.response.user.*;
import com.heartz.byeboo.application.port.out.quest.RetrieveQuestPort;
import com.heartz.byeboo.application.port.out.user.*;
import com.heartz.byeboo.application.port.out.userquest.RetrieveUserQuestPort;
import com.heartz.byeboo.constants.QuestConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserJourneyErrorCode;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.domain.type.ECharacterDialogue;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import com.heartz.byeboo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {
    private final CreateUserPort createUserPort;
    private final CreateUserJourneyPort createUserJourneyPort;
    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveUserJourneyPort retrieveUserJourneyPort;
    private final RetrieveQuestPort retrieveQuestPort;
    private final RetrieveUserQuestPort retrieveUserQuestPort;
    private final UpdateUserPort updateUserPort;
    private final UpdateUserJourneyPort updateUserJourneyPort;

    @Override
    @Transactional
    public UserCreateResponseDto updateUser(UserCreateCommand userCreateCommand) {
        User currentUser = retrieveUserPort.getUserById(userCreateCommand.getUserId());
       //User currentUser = UserMapper.commandToDomain(userCreateCommand);
        currentUser.initializeCurrentNumber();
        currentUser.updateName(userCreateCommand.getName());
        currentUser.updateQuestStyle(userCreateCommand.getQuestStyle());
        //User savedUser = createUserPort.createUser(currentUser);
        updateUserPort.updateUser(currentUser);

        List<UserJourney> userJourneyList = UserJourney.initializeUserJourney(currentUser);
        createUserJourneyPort.createUserJourney(userJourneyList);

        return UserCreateResponseDto.of(currentUser.getId(), currentUser.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public UserNameResponseDto getUserName(UserNameCommand userNameCommand) {
        User currentUser = retrieveUserPort.getUserById(userNameCommand.getId());

        return UserNameResponseDto.of(currentUser.getName());
    }

    @Override
    @Transactional
    public UserNameResponseDto updateUserName(UserNameUpdateCommand userNameUpdateCommand) {
        User currentUser = retrieveUserPort.getUserById(userNameUpdateCommand.getId());
        currentUser.updateName(userNameUpdateCommand.getName());

        updateUserPort.updateName(currentUser);

        return UserNameResponseDto.of(currentUser.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public UserJourneyResponseDto getUserJourney(UserJourneyCommand userJourneyCommand) {
        User currentUser = retrieveUserPort.getUserById(userJourneyCommand.getId());
        UserJourney userJourney = retrieveUserJourneyPort.getOngoingUserJourneyByUser(currentUser);

        return getUseJourneyResponseDto(userJourney);
    }

    private UserJourneyResponseDto getUseJourneyResponseDto(UserJourney userJourney) {
//        if(userJourney.getJourneyStatus().equals(EJourneyStatus.BEFORE_START))
            return UserJourneyResponseDto.of(
                    userJourney.getJourney().getLabel(),
                    userJourney.getJourney().getDescription()
            );
//        else
//            return UserJourneyResponseDto.of(
//                    userJourney.getJourney().getLabel(),
//                    null
//            );
    }

    @Override
    @Transactional(readOnly = true)
    public HomeCountResponseDto getCompletedCount(CompletedCountCommand completedCountCommand) {
        User currentUser = retrieveUserPort.getUserById(completedCountCommand.getId());
        Boolean todayCompleted = Boolean.FALSE;

        if(isBeforeStart(currentUser))
            throw new CustomException(UserQuestErrorCode.NOT_FOUND_ONGOING_USER_QUEST);

        if(isInitialStart(currentUser))
            return HomeCountResponseDto.of(todayCompleted, 0L);

        if(isTodayCompleted(getRecentUserQuestByUser(currentUser)))
            todayCompleted = Boolean.TRUE;

        return HomeCountResponseDto.of(todayCompleted, currentUser.getCurrentNumber() - 1);
    }

    private UserQuest getRecentUserQuestByUser(User currentUser) {
        UserJourney ongoingUserJourney = retrieveUserJourneyPort.getOngoingUserJourneyByUser(currentUser);
        Quest quest = retrieveQuestPort.getQuestByJourneyAndQuestNumber(
                ongoingUserJourney.getJourney(),
                currentUser.getCurrentNumber() - 1
        );
        return retrieveUserQuestPort.getRecentUserQuestByUserAndQuest(currentUser, quest);
    }

    @Override
    @Transactional
    public Void updateInitialUserJourney(UserJourneyUpdateCommand userJourneyUpdateCommand) {
        User currentUser = retrieveUserPort.getUserById(userJourneyUpdateCommand.getId());
        UserJourney ongoingUserJourney = retrieveUserJourneyPort.getOngoingUserJourneyByUser(currentUser);

        if(!ongoingUserJourney.getJourneyStatus().equals(EJourneyStatus.BEFORE_START))
            throw new CustomException(UserJourneyErrorCode.CONFLICT_USER_JOURNEY_STATUS);

        ongoingUserJourney.updateInitialUserJourney();
        updateUserJourneyPort.updateUserJourney(ongoingUserJourney);
        currentUser.updateCurrentNumber();
        updateUserPort.updateCurrentNumber(currentUser);

        return null;
    }

    @Override
    @Transactional
    public UserCharacterResponseDto getCharacterDialogue(
            UserCharacterDialogueCommand userCharacterDialogueCommand
    ) {
        User currentUser = retrieveUserPort.getUserById(userCharacterDialogueCommand.getId());
        ECharacterDialogue dialogue;

        if (isBeforeStart(currentUser))
            return UserCharacterResponseDto.of(
                    ECharacterDialogue.BEFORE_START.getDialogue(currentUser.getName())
            );
        if (isInitialStart(currentUser))
            return UserCharacterResponseDto.of(
                    ECharacterDialogue.START.getDialogue(currentUser.getName())
            );

        if(!isTodayCompleted(getRecentUserQuestByUser(currentUser))) {
            dialogue = ECharacterDialogue.START;
        } else if (isCompleted(currentUser)) {
            dialogue = ECharacterDialogue.COMPLETED;
        } else {
            dialogue = ECharacterDialogue.IN_PROGRESS;
        }

        return UserCharacterResponseDto.of(dialogue.getDialogue(currentUser.getName()));
    }

    private Boolean isBeforeStart(User user) {
        return user.getCurrentNumber() == QuestConstants.QUEST_BEFORE_START_COUNT;
    }

    private Boolean isInitialStart(User user) {
        return user.getCurrentNumber() == QuestConstants.QUEST_INITIAL_START_COUNT;
    }

    private Boolean isCompleted(User user) {
        return user.getCurrentNumber() == QuestConstants.QUEST_COUNT_MAX;
    }

    private Boolean isTodayCompleted(UserQuest recentUserQuest) {
        return recentUserQuest.getCreatedDate().plusDays(1).isAfter(LocalDateTime.now());
    }
}