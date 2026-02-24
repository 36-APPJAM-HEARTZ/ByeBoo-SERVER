package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.application.command.userblock.UserBlockCommand;
import com.heartz.byeboo.application.command.userblock.UserBlockDeleteCommand;
import com.heartz.byeboo.application.port.in.dto.response.userblock.UserBlockListResponseDto;
import com.heartz.byeboo.application.port.in.usecase.UserBlockUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blocks")
@Tag(name = "BLOCK API", description = "사용자 차단에 대한 API입니다.")
public class UserBlockController {

    private final UserBlockUseCase userBlockUseCase;

    @Operation(
            summary = "사용자 차단",
            description = "사용자 차단하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "사용자 차단 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일떄"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PostMapping("/{blockedUserId}")
    public BaseResponse<Void> blockUser(
            @UserId final Long userId,
            @PathVariable final Long blockedUserId){
        UserBlockCommand command = UserBlockCommand.from(userId, blockedUserId);
        userBlockUseCase.block(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "사용자 차단 목록 조회",
            description = "사용자 차단 목록 조회하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "사용자 차단 목록 조회 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일떄"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @GetMapping
    public BaseResponse<UserBlockListResponseDto> getBLockedUser(
            @UserId final Long userId
    ){
        return BaseResponse.success(userBlockUseCase.getUserBlockList(userId));
    }

    @Operation(
            summary = "사용자 차단 해제",
            description = "사용자 차단 해제하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "사용자 차단 해제 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일떄"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @DeleteMapping({"/{blockId}"})
    public BaseResponse<Void> unblock(
            @UserId final Long userId,
            @PathVariable final Long blockId
    ){
        UserBlockDeleteCommand userBlockDeleteCommand = UserBlockDeleteCommand.from(userId, blockId);
        return BaseResponse.success(userBlockUseCase.unblock(userBlockDeleteCommand));
    }

}
