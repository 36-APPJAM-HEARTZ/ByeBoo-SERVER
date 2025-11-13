package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.NotificationTokenRequestDto;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenDeleteCommand;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenSaveCommand;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenUpdateCommand;
import com.heartz.byeboo.application.port.in.usecase.NotificationTokenUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification-tokens")
public class NotificationTokenController {

    private final NotificationTokenUseCase notificationTokenUseCase;

    @Operation(
            summary = "알림 토큰 저장 API",
            description = "알림 토큰 저장하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "토큰 저장 성공"
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
    @PostMapping
    public BaseResponse<Void> saveToken(
            @UserId Long userId,
            @RequestBody NotificationTokenRequestDto requestDto
            ){
        return BaseResponse.success(notificationTokenUseCase.saveToken(NotificationTokenSaveCommand.of(userId, requestDto.token())));
    }

    @Operation(
            summary = "알림 토큰 타임스탬프 업데이트 API",
            description = "알림 토큰 타임스탬프 업데이트하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "알림 토큰 타임스탬프 업데이트 성공"
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
    @PatchMapping
    public BaseResponse<Void> updateTimeStamp(
            @UserId Long userId,
            @RequestBody NotificationTokenRequestDto requestDto
    ){
        return BaseResponse.success(notificationTokenUseCase.updateTimeStamp(NotificationTokenUpdateCommand.of(userId, requestDto.token())));
    }

    @Operation(
            summary = "알림 토큰 삭제 API",
            description = "알림 토큰 삭제하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "토큰 삭제 성공"
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
    @DeleteMapping
    public BaseResponse<Void> deleteToken(
            @UserId Long userId,
            @RequestBody NotificationTokenRequestDto requestDto
    ){
        return BaseResponse.success(notificationTokenUseCase.deleteToken(NotificationTokenDeleteCommand.of(userId, requestDto.token())));
    }
}
