package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.CommonQuestCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.CommonQuestUpdateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.RecordingQuestCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.RecordingQuestUpdateRequestDto;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestCreateCommand;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestDeleteCommand;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestListCommand;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestUpdateCommand;
import com.heartz.byeboo.application.command.userquest.CompletedJourneyCommand;
import com.heartz.byeboo.application.command.userquest.RecordingQuestCreateCommand;
import com.heartz.byeboo.application.command.userquest.RecordingQuestUpdateCommand;
import com.heartz.byeboo.application.port.in.dto.response.usercommonquest.UserCommonQuestListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.JourneyListResponseDto;
import com.heartz.byeboo.application.port.in.usecase.UserCommonQuestUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/common-quests")
@Tag(name = "Common Quest API", description = "공통 퀘스트 대한 API입니다.")
public class UerCommonQuestController {

    private final UserCommonQuestUseCase userCommonQuestUseCase;

    @Operation(
            summary = "공통 퀘스트 답변 저장",
            description = "공통 퀘스트에 대한 답변을 저장하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "공통 퀘스트 답변 저장 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일떄"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "퀘스트 답변 길이가 500 초과일때"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "퀘스트 답변 길이가 10보다 작을때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PostMapping("/{questId}")
    public BaseResponse<Void> createCommonQuest(
            @UserId final Long userId,
            @RequestBody final CommonQuestCreateRequestDto commonQuestCreateRequestDto,
            @PathVariable final Long questId){
        CommonQuestCreateCommand command = CommonQuestCreateCommand.from(commonQuestCreateRequestDto, questId, userId);
        userCommonQuestUseCase.createCommonQuest(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "공통 퀘스트 답변 삭제",
            description = "공통 퀘스트에 대한 답변을 삭제하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "공통 퀘스트 답변 삭제 성공"
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
    @DeleteMapping("/{answerId}")
    public BaseResponse<Void> deleteCommonQuest(
            @UserId final Long userId,
            @PathVariable final Long answerId){
        CommonQuestDeleteCommand command = CommonQuestDeleteCommand.from(answerId, userId);
        userCommonQuestUseCase.deleteCommonQuest(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "공통 퀘스트 답변 수정",
            description = "공통 퀘스트에 대한 답변을 수정하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "공통 퀘스트 답변 수정 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일떄"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "공통 퀘스트 답변 길이가 500 초과일때"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "공통 퀘스트 답변 길이가 10보다 작을때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PatchMapping("/{answerId}")
    public BaseResponse<Void> updateCommonQuest(
            @UserId final Long userId,
            @RequestBody final CommonQuestUpdateRequestDto commonQuestUpdateRequestDto,
            @PathVariable final Long answerId){
        CommonQuestUpdateCommand command = CommonQuestUpdateCommand.from(commonQuestUpdateRequestDto, answerId, userId);
        userCommonQuestUseCase.updateCommonQuest(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "날짜별 공통 퀘스트 전체 조회",
            description = "날짜별 공통 퀘스트 전체 조회하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "공통 퀘스트 전체 조회 성공"
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
    public BaseResponse<UserCommonQuestListResponseDto> getCommonQuest(
            @UserId final Long userId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(value = "cursor", required = false) Long cursor,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ){
        CommonQuestListCommand command = CommonQuestListCommand.from(userId, date, cursor, limit);
        return BaseResponse.success(userCommonQuestUseCase.getListCommonQuest(command));
    }
}
