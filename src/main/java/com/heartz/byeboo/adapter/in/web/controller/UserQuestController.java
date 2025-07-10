package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.ActiveQuestRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.RecordingQuestRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.SignedUrlRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.response.userquest.JourneyListResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.userquest.UserQuestDetailResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.SignedUrlResponseDto;
import com.heartz.byeboo.application.command.*;
import com.heartz.byeboo.application.command.userquest.*;
import com.heartz.byeboo.application.port.in.UserQuestUseCase;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quests")
public class UserQuestController {

    private final UserQuestUseCase userQuestUseCase;

    @Operation(
            summary = "퀘스트 답변 저장(질문/답변형)",
            description = "질문/답변형 퀘스트에 대한 답변을 저장하는 API입니다."
    )
    @PostMapping("/{questId}/recording")
    public BaseResponse<Void> createRecordingQuest(
            @RequestHeader final Long userId,
            @RequestBody final RecordingQuestRequestDto recordingQuestRequestDto,
            @PathVariable final Long questId){
        RecordingQuestCreateCommand command = RecordingQuestCreateCommand.from(recordingQuestRequestDto, questId, userId);
        userQuestUseCase.createRecordingQuest(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "퀘스트 답변 저장(행동형)",
            description = "퀘스트에 대한 답변을 저장하는 API입니다. (사진 제외, 텍스트만!)"
    )
    @PostMapping("/{questId}/active")
    public BaseResponse<Void> createActiveQuest(
            @RequestHeader final Long userId,
            @RequestBody final ActiveQuestRequestDto activeQuestRequestDto,
            @PathVariable final Long questId){
        ActiveQuestCreateCommand command = ActiveQuestCreateCommand.from(activeQuestRequestDto, questId, userId);
        userQuestUseCase.createActiveQuest(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "Signed URL 요청 (행동형 퀘스트)",
            description = "사진 첨부 (+ 버튼) 눌렀을때 Presigned Url을 요청하는 API 입니다."
                    +"클라이언트는 발급받은 presigned url에 PUT 요청을 해서 이미지를 업로드하면 됩니다. (완료하기 버튼 누르면 업로드 해주세요)"
    )
    @PostMapping("/images/signed-url")
    public BaseResponse<SignedUrlResponseDto> createSignedUrl(
            @RequestHeader final Long userId,
            @RequestBody final SignedUrlRequestDto signedUrlRequestDto
            ){
        SignedUrlCreateCommand command = SignedUrlCreateCommand.of(signedUrlRequestDto, userId);
        return BaseResponse.success(userQuestUseCase.getSignedUrl(command));
    }

    @Operation(
            summary = "작성된 퀘스트 상세 조회",
            description = "기록한 퀘스트를 조회하는 API입니다."
    )
    @GetMapping("/answer/{questId}")
    public BaseResponse<UserQuestDetailResponseDto> getDetailQuest(
            @RequestHeader final Long userId,
            @PathVariable final Long questId
    ){
        UserQuestDetailCommand command = UserQuestDetailCommand.of(questId, userId);

        return BaseResponse.success(userQuestUseCase.getDetailQuest(command));
    }

    @Operation(
            summary = "완료된 퀘스트 여정 조회",
            description = "완료된 여정을 조회하는 API입니다."
    )
    @GetMapping("/journey")
    public BaseResponse<JourneyListResponseDto> getCompletedJourney(
            @RequestHeader final Long userId
    ){
        CompletedJourneyCommand command = CompletedJourneyCommand.of(userId);
        return BaseResponse.success(userQuestUseCase.getCompletedJourney(command));
    }

    @Operation(
            summary = "새로운 여정 시작하기",
            description = "새로운 여정을 눌렀을 때 보내야하는 API입니다."
    )
    @PostMapping("/journey")
    public BaseResponse<Void> createJourney(
            @RequestHeader final Long userId,
            @RequestParam final String journey
    ){
        JourneyUpdateCommand command = JourneyUpdateCommand.of(userId, journey);
        userQuestUseCase.updateJourneyStatus(command);
        return BaseResponse.success(null);
    }

}
