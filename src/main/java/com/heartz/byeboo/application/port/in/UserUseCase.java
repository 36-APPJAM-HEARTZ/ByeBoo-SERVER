package com.heartz.byeboo.application.port.in;

import com.heartz.byeboo.adapter.in.web.dto.response.HomeCountResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.UserCreateResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.UserJourneyResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.UserNameResponseDto;
import com.heartz.byeboo.application.command.HomeCountCommand;
import com.heartz.byeboo.application.command.UserCreateCommand;
import com.heartz.byeboo.application.command.UserJourneyCommand;
import com.heartz.byeboo.application.command.UserNameCommand;

public interface UserUseCase {
    UserCreateResponseDto createUser(UserCreateCommand userCreateCommand);
    UserNameResponseDto getUserName(UserNameCommand userNameCommand);
    UserJourneyResponseDto getUserJourney(UserJourneyCommand userJourneyCommand);
    HomeCountResponseDto getHomeCount(HomeCountCommand homeCountCommand);
}
