package com.heartz.byeboo.application.service;

import com.heartz.byeboo.adapter.in.web.dto.response.HomeCountResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.UserCreateResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.UserJourneyResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.UserNameResponseDto;
import com.heartz.byeboo.application.command.HomeCountCommand;
import com.heartz.byeboo.application.command.UserCreateCommand;
import com.heartz.byeboo.application.command.UserJourneyCommand;
import com.heartz.byeboo.application.command.UserNameCommand;
import com.heartz.byeboo.application.port.in.UserUseCase;
import com.heartz.byeboo.application.port.out.*;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.model.UserQuest;
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

    @Override
    @Transactional
    public UserCreateResponseDto createUser(UserCreateCommand userCreateCommand) {
        User user = UserMapper.commandToDomain(userCreateCommand);
        user.initializeCurrentNumber();
        User savedUser = createUserPort.createUser(user);

        List<UserJourney> userJourneyList = UserJourney.initializeUserJourney(savedUser);
        createUserJourneyPort.createUserJourney(userJourneyList);

        return UserCreateResponseDto.of(savedUser.getId(), savedUser.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public UserNameResponseDto getUserName(UserNameCommand userNameCommand) {
        User user = retrieveUserPort.getUserById(userNameCommand.getId());

        return UserNameResponseDto.of(user.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public UserJourneyResponseDto getUserJourney(UserJourneyCommand userJourneyCommand) {
        User currentUser = retrieveUserPort.getUserById(userJourneyCommand.getId());
        UserJourney userJourney = retrieveUserJourneyPort.getUserJourneyByUser(currentUser);

        return getUseJourneyResponseDto(userJourney);
    }

    private UserJourneyResponseDto getUseJourneyResponseDto(UserJourney userJourney) {
        if(userJourney.getJourneyStatus().equals(EJourneyStatus.BEFORE_START))
            return UserJourneyResponseDto.of(
                    userJourney.getJourney().getLabel(),
                    userJourney.getJourney().getDescription()
            );
        else
            return UserJourneyResponseDto.of(
                    userJourney.getJourney().getLabel(),
                    null
            );
    }

    @Override
    @Transactional(readOnly = true)
    public HomeCountResponseDto getHomeCount(HomeCountCommand homeCountCommand) {
        User currentUser = retrieveUserPort.getUserById(homeCountCommand.getId());
        UserJourney ongoingUserJourney = retrieveUserJourneyPort.getUserJourneyByUser(currentUser);

        if(currentUser.getCurrentNumber() == 0)
            throw new CustomException(UserQuestErrorCode.NOT_FOUND_ONGOING_USER_QUEST);

        Quest quest = retrieveQuestPort.getQuestByJourneyAndStepNumber(
                ongoingUserJourney.getJourney(),
                currentUser.getCurrentNumber()
        );
        UserQuest recentUserQuest = retrieveUserQuestPort.getRecentUserQuestByUserAndQuest(currentUser, quest);
        Boolean todayCompleted = Boolean.FALSE;

        if(recentUserQuest == null)
            return HomeCountResponseDto.of(todayCompleted, 0L);

        if(recentUserQuest.getCreatedDate().plusDays(1).isAfter(LocalDateTime.now()))
            todayCompleted = Boolean.TRUE;

        return HomeCountResponseDto.of(todayCompleted, currentUser.getCurrentNumber() - 1);
    }
}