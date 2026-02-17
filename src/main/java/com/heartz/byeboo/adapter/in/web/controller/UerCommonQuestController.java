package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.CommonQuestCreateRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.RecordingQuestCreateRequestDto;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestCreateCommand;
import com.heartz.byeboo.application.command.userquest.RecordingQuestCreateCommand;
import com.heartz.byeboo.application.port.in.usecase.UserCommonQuestUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
