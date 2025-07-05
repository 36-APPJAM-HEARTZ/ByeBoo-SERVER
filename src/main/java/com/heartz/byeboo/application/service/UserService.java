package com.heartz.byeboo.application.service;

import com.heartz.byeboo.adapter.in.web.dto.UserCreateResponseDto;
import com.heartz.byeboo.application.command.UserCreateCommand;
import com.heartz.byeboo.application.port.in.UserUseCase;
import com.heartz.byeboo.application.port.out.CreateUserJourneyPort;
import com.heartz.byeboo.application.port.out.CreateUserPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
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
}
