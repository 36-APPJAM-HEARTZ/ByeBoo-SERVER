package com.heartz.byeboo.application.port.in;

import com.heartz.byeboo.adapter.in.web.dto.UserCreateResponseDto;
import com.heartz.byeboo.application.command.UserCreateCommand;

public interface UserUseCase {
    UserCreateResponseDto createUser(UserCreateCommand userCreateCommand);
}
