package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.UserCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.response.*;
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

    @GetMapping("/users/count")
    public BaseResponse<HomeCountResponseDto> getCompletedCount(@RequestHeader Long userId) {
        CompletedCountCommand completedCountCommand = CompletedCountCommand.of(userId);
        return BaseResponse.success(userUseCase.getCompletedCount(completedCountCommand));
    }

    @PatchMapping("/users/journey/start")
    public BaseResponse<Void> updateInitialUserJourney(@RequestHeader Long userId) {
        UserJourneyUpdateCommand userJourneyUpdateCommand = UserJourneyUpdateCommand.of(userId);
        return BaseResponse.success(userUseCase.updateInitialUserJourney(userJourneyUpdateCommand));
    }

    @GetMapping("/users/character")
    public BaseResponse<UserCharacterResponseDto> getCharacterDialogue(@RequestHeader Long userId) {
        UserCharacterDialogueCommand userCharacterDialogueCommand = UserCharacterDialogueCommand.of(userId);
        return BaseResponse.success(userUseCase.getCharacterDialogue(userCharacterDialogueCommand));
    }
}
