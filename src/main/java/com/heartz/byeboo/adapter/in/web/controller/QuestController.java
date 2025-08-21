package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.application.port.in.dto.response.quest.AllQuestCompletedResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.quest.AllQuestProgressResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.quest.QuestDetailResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.quest.TipListResponseDto;
import com.heartz.byeboo.application.command.quest.AllQuestCompletedCommand;
import com.heartz.byeboo.application.command.quest.AllQuestProgressCommand;
import com.heartz.byeboo.application.command.quest.QuestDetailCommand;
import com.heartz.byeboo.application.command.quest.QuestTipCommand;
import com.heartz.byeboo.application.port.in.usecase.QuestUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quests")
@Tag(name = "QUEST API", description = "퀘스트에 대한 API입니다.")
public class QuestController {

    private final QuestUseCase questUseCase;

    @Operation(
            summary = "퀘스트 팁 조회",
            description = "퀘스트 팁을 조회하기 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "퀘스트 팁 조회 성공"
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
    @GetMapping("/{questId}/tip")
    public BaseResponse<TipListResponseDto> getQuestTip(
            @UserId final Long userId,
            @PathVariable final Long questId
    ){
        QuestTipCommand command = QuestTipCommand.of(userId, questId);
        return BaseResponse.success( questUseCase.getQuestTip(command));
    }

    @Operation(
            summary = "진행중인 여정 전체 퀘스트 조회",
            description = "진행중인 여정의 전체 퀘스트 조회하기 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "전체 퀘스트 조회 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "시작 전 여정일때"
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
                            responseCode = "409",
                            description = "진행되었어야 하는 퀘스트가 없을때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @GetMapping("/all/progress")
    public BaseResponse<AllQuestProgressResponseDto> getAllQuest(
            @UserId final Long userId
    ){
        AllQuestProgressCommand allQuestProgressCommand = AllQuestProgressCommand.of(userId);
        return BaseResponse.success(questUseCase.getProgressAllQuest(allQuestProgressCommand));
    }

    @Operation(
            summary = "완료된 여정 전체 퀘스트 조회",
            description = "완료된 여정의 전체 퀘스트 조회하기 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "전체 퀘스트 조회 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "올바르지 않은 여정일때"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "완료되지 않은 여정일때"
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
    @GetMapping("/all/completed")
    public BaseResponse<AllQuestCompletedResponseDto> getAllQuest(
            @UserId final Long userId,
            @RequestParam final String journey
    ){
        AllQuestCompletedCommand allQuestCompletedCommand = AllQuestCompletedCommand.of(userId, journey);
        return BaseResponse.success(questUseCase.getCompletedAllQuest(allQuestCompletedCommand));
    }

    @Operation(
            summary = "상세 퀘스트 조회",
            description = "상세 퀘스트 조회하기 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "전체 퀘스트 조회 성공"
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
    @GetMapping("/{questId}")
    public BaseResponse<QuestDetailResponseDto> getQuestDetail(
            @UserId final Long userId
            ,@PathVariable final Long questId
    ){
        QuestDetailCommand questDetailCommand = QuestDetailCommand.of(userId, questId);
        return BaseResponse.success(questUseCase.getQuestDetail(questDetailCommand));
    }
}
