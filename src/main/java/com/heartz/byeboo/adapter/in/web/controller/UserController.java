package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.UserCreateRequestDto;
import com.heartz.byeboo.application.command.user.*;
import com.heartz.byeboo.application.command.userquest.CompletedCountCommand;
import com.heartz.byeboo.application.port.in.usecase.UserUseCase;
import com.heartz.byeboo.application.port.in.dto.response.user.*;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserUseCase userUseCase;

    @Operation(
            summary = "유저 생성",
            description = "유저를 생성하기 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "유저 생성 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "올바르지 않은 퀘스트 방식을 보냈을때"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "유저 이름 길이가 2글자 이하일때"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "유저 이름 길이가 5글자 이상일때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PostMapping("/users")
    public BaseResponse<UserCreateResponseDto> createUser(
            @RequestBody UserCreateRequestDto userCreateRequestDto
    ) {
        UserCreateCommand userCreateCommand = UserCreateCommand.from(userCreateRequestDto);
        return BaseResponse.success(userUseCase.createUser(userCreateCommand));
    }

    @Operation(
            summary = "유저 이름 조회",
            description = "유저 이름을 조회하기 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "유저 이름 조회 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @GetMapping("/users")
    public BaseResponse<UserNameResponseDto> getUserName(@RequestHeader Long userId) {
        UserNameCommand userNameCommand = UserNameCommand.of(userId);
        return BaseResponse.success(userUseCase.getUserName(userNameCommand));
    }

    @Operation(
            summary = "유저 여정 조회",
            description = "유저의 여정을 조회하기 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "유저 여정 조회 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 여정일때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @GetMapping("/users/journey")
    public BaseResponse<UserJourneyResponseDto> getUserJourney(@RequestHeader Long userId) {
        UserJourneyCommand userJourneyCommand = UserJourneyCommand.of(userId);
        return BaseResponse.success(userUseCase.getUserJourney(userJourneyCommand));
    }

    @Operation(
            summary = "완료 퀘스트 개수 조회",
            description = "완료 퀘스트 개수를 조회하기 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "완료 퀘스트 개수 조회 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "진행하고 있는 퀘스트가 존재하지 않을때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @GetMapping("/users/count")
    public BaseResponse<HomeCountResponseDto> getCompletedCount(@RequestHeader Long userId) {
        CompletedCountCommand completedCountCommand = CompletedCountCommand.of(userId);
        return BaseResponse.success(userUseCase.getCompletedCount(completedCountCommand));
    }

    @Operation(
            summary = "여정 시작",
            description = "시작 전 상태인 여정을 진행중으로 바꾸기 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "여정 시작 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "이미 진행하고 있는 여정을 시작할때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PatchMapping("/users/journey/start")
    public BaseResponse<Void> updateInitialUserJourney(@RequestHeader Long userId) {
        UserJourneyUpdateCommand userJourneyUpdateCommand = UserJourneyUpdateCommand.of(userId);
        return BaseResponse.success(userUseCase.updateInitialUserJourney(userJourneyUpdateCommand));
    }

    @Operation(
            summary = "캐릭터 대사 조회",
            description = "유저 상태에 맞게 캐릭터 대사를 조회하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "여정 시작 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "이미 진행하고 있는 여정을 시작할때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @GetMapping("/users/character")
    public BaseResponse<UserCharacterResponseDto> getCharacterDialogue(@RequestHeader Long userId) {
        UserCharacterDialogueCommand userCharacterDialogueCommand = UserCharacterDialogueCommand.of(userId);
        return BaseResponse.success(userUseCase.getCharacterDialogue(userCharacterDialogueCommand));
    }
}
