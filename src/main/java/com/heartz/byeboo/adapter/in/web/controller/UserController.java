package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.UserCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.response.HomeCountResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.UserCreateResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.UserJourneyResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.UserNameResponseDto;
import com.heartz.byeboo.application.command.*;
import com.heartz.byeboo.application.port.in.UserUseCase;
import com.heartz.byeboo.core.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users")
    public BaseResponse<UserNameResponseDto> getUserName(@RequestHeader Long userId) {
        UserNameCommand userNameCommand = UserNameCommand.of(userId);
        return BaseResponse.success(userUseCase.getUserName(userNameCommand));
    }

    @GetMapping("/users/journey")
    public BaseResponse<UserJourneyResponseDto> getUserJourney(@RequestHeader Long userId) {
        UserJourneyCommand userJourneyCommand = UserJourneyCommand.of(userId);
        return BaseResponse.success(userUseCase.getUserJourney(userJourneyCommand));
    }

    @GetMapping("/home/count")
    public BaseResponse<HomeCountResponseDto> getHomeCount(@RequestHeader Long userId) {
        HomeCountCommand homeCountCommand = HomeCountCommand.of(userId);
        return BaseResponse.success(userUseCase.getHomeCount(homeCountCommand));
    }

    @PatchMapping("/home")
    public BaseResponse<Void> updateInitialUserJourney(@RequestHeader Long userId) {
        UserJourneyUpdateCommand userJourneyUpdateCommand = UserJourneyUpdateCommand.of(userId);
        return BaseResponse.success(userUseCase.updateInitialUserJourney(userJourneyUpdateCommand));
    }
}
