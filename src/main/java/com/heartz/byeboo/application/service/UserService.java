package com.heartz.byeboo.application.service;

import com.heartz.byeboo.adapter.in.web.dto.UserCreateResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.UserJourneyResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.UserNameResponseDto;
import com.heartz.byeboo.application.command.UserCreateCommand;
import com.heartz.byeboo.application.command.UserJourneyCommand;
import com.heartz.byeboo.application.command.UserNameCommand;
import com.heartz.byeboo.application.port.in.UserUseCase;
import com.heartz.byeboo.application.port.out.CreateUserJourneyPort;
import com.heartz.byeboo.application.port.out.CreateUserPort;
import com.heartz.byeboo.application.port.out.RetrieveUserJourneyPort;
import com.heartz.byeboo.application.port.out.RetrieveUserPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import com.heartz.byeboo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {
    private final CreateUserPort createUserPort;
    private final CreateUserJourneyPort createUserJourneyPort;
    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveUserJourneyPort retrieveUserJourneyPort;

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
}
