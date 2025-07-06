package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.UserCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.UserCreateResponseDto;
import com.heartz.byeboo.application.command.UserCreateCommand;
import com.heartz.byeboo.application.port.in.UserUseCase;
import com.heartz.byeboo.core.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserUseCase userUseCase;

    @PostMapping("/users")
    public BaseResponse<UserCreateResponseDto> createUser(
            @RequestBody UserCreateRequestDto userCreateRequestDto
    ) {
        UserCreateCommand userCreateCommand = UserCreateCommand.from(userCreateRequestDto);
        return BaseResponse.success(userUseCase.createUser(userCreateCommand));
    }
}
