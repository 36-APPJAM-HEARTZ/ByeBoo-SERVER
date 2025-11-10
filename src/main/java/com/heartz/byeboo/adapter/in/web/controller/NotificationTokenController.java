package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.NotificationTokenRequestDto;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenDeleteCommand;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenSaveCommand;
import com.heartz.byeboo.application.command.notificationtoken.NotificationTokenUpdateCommand;
import com.heartz.byeboo.application.port.in.usecase.NotificationTokenUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification-tokens")
public class NotificationTokenController {

    private final NotificationTokenUseCase notificationTokenUseCase;

    @PostMapping
    public BaseResponse<Void> saveToken(
            @UserId Long userId,
            @RequestBody NotificationTokenRequestDto requestDto
            ){
        return BaseResponse.success(notificationTokenUseCase.saveToken(NotificationTokenSaveCommand.of(userId, requestDto.token())));
    }

    @PatchMapping
    public BaseResponse<Void> updateTimeStamp(
            @UserId Long userId,
            @RequestBody NotificationTokenRequestDto requestDto
    ){
        return BaseResponse.success(notificationTokenUseCase.updateTimeStamp(NotificationTokenUpdateCommand.of(userId, requestDto.token())));
    }

    @DeleteMapping
    public BaseResponse<Void> deleteToken(
            @UserId Long userId,
            @RequestBody NotificationTokenRequestDto requestDto
    ){
        return BaseResponse.success(notificationTokenUseCase.deleteToken(NotificationTokenDeleteCommand.of(userId, requestDto.token())));
    }
}
