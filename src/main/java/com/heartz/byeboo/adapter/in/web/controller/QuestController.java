package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.response.quest.AllQuestResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.quest.QuestDetailResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.quest.TipListResponseDto;
import com.heartz.byeboo.application.command.quest.AllQuestCommand;
import com.heartz.byeboo.application.command.quest.QuestDetailCommand;
import com.heartz.byeboo.application.command.quest.QuestTipCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
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
            @RequestHeader final Long userId,
            @PathVariable final Long questId
    ){
        QuestTipCommand command = QuestTipCommand.of(userId, questId);
        return BaseResponse.success( questUseCase.getQuestTip(command));
    }

    @Operation(
            summary = "전체 퀘스트 조회",
            description = "전체 퀘스트 조회하기 위한 API입니다.",
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
                            responseCode = "404",
                            description = "존재하지 않는 여정일때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @GetMapping("/all")
    public BaseResponse<AllQuestResponseDto> getAllQuest(
            @RequestHeader final Long userId,
            @RequestParam final String journey
    ){
        AllQuestCommand allQuestCommand = AllQuestCommand.of(userId, journey);
        return BaseResponse.success(questUseCase.getAllQuest(allQuestCommand));
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
            @RequestHeader final Long userId,
            @PathVariable final Long questId
    ){
        QuestDetailCommand questDetailCommand = QuestDetailCommand.of(userId, questId);
        return BaseResponse.success(questUseCase.getQuestDetail(questDetailCommand));
    }
}
