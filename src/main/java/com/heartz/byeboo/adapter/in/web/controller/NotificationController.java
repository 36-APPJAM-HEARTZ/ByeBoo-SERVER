package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.ReplyCreateRequestDto;
import com.heartz.byeboo.application.command.comment.ReplyCreateCommand;
import com.heartz.byeboo.application.command.notification.NotificationUpdateCommand;
import com.heartz.byeboo.application.port.in.dto.response.notification.NotificationListResponseDto;
import com.heartz.byeboo.application.port.in.usecase.NotificationUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationUseCase notificationUseCase;

    @Operation(
            summary = "알림 목록 조회",
            description = "알림 목록 조회하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "알림 목록 조회 성공"
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

    @GetMapping
    public BaseResponse<NotificationListResponseDto> getNotificationList(
            @UserId Long userId
    ){
        return BaseResponse.success(notificationUseCase.getListNotification(userId));
    }

    @Operation(
            summary = "알림 읽기(터치)",
            description = "알림 읽기(터치)하는 API 입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "알림 읽기(터치) 성공"
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

    @PatchMapping("/{notificationId}/read")
    public BaseResponse<Void> readNotification(
            @UserId Long userId,
            @PathVariable("notificationId") Long notificationId
    ){
        NotificationUpdateCommand command = NotificationUpdateCommand.of(userId, notificationId);
        return BaseResponse.success(notificationUseCase.readNotification(command));
    }
}
