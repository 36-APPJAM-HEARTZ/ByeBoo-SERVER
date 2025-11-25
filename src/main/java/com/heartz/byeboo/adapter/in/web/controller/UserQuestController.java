package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.*;
import com.heartz.byeboo.application.port.in.dto.response.userquest.JourneyListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userquest.UserQuestDetailResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.SignedUrlResponseDto;
import com.heartz.byeboo.application.command.*;
import com.heartz.byeboo.application.command.userquest.*;
import com.heartz.byeboo.application.port.in.usecase.UserQuestUseCase;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quests")
public class UserQuestController {

    private final UserQuestUseCase userQuestUseCase;

    @Operation(
            summary = "퀘스트 답변 저장(질문/답변형)",
            description = "질문/답변형 퀘스트에 대한 답변을 저장하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "질문형 퀘스트 답변 저장 성공"
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
    @PostMapping("/{questId}/recording")
    public BaseResponse<Void> createRecordingQuest(
            @UserId final Long userId,
            @RequestBody final RecordingQuestCreateRequestDto recordingQuestCreateRequestDto,
            @PathVariable final Long questId){
        RecordingQuestCreateCommand command = RecordingQuestCreateCommand.from(recordingQuestCreateRequestDto, questId, userId);
        userQuestUseCase.createRecordingQuest(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "퀘스트 답변 저장(행동형)",
            description = "퀘스트에 대한 답변을 저장하는 API입니다. (사진 제외, 텍스트만!)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "퀘스트 답변 저장 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "퀘스트 답변 길이가 200 초과일때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "버킷에서 이미지를 찾을 수 없을때"
                    )
            }
    )
    @PostMapping("/{questId}/active")
    public BaseResponse<Void> createActiveQuest(
            @UserId final Long userId,
            @RequestBody final ActiveQuestCreateRequestDto activeQuestCreateRequestDto,
            @PathVariable final Long questId){
        ActiveQuestCreateCommand command = ActiveQuestCreateCommand.from(activeQuestCreateRequestDto, questId, userId);
        userQuestUseCase.createActiveQuest(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "Signed URL 요청 (행동형 퀘스트)",
            description = "사진 첨부 (+ 버튼) 눌렀을때 Presigned Url을 요청하는 API 입니다."
                    +"클라이언트는 발급받은 presigned url에 PUT 요청을 해서 이미지를 업로드하면 됩니다. (완료하기 버튼 누르면 업로드 해주세요)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Signed URL 요청 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "UUID 형식이 올바르지 않을때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PostMapping("/images/signed-url")
    public BaseResponse<SignedUrlResponseDto> createSignedUrl(
            @UserId final Long userId,
            @RequestBody final SignedUrlRequestDto signedUrlRequestDto
            ){
        SignedUrlCreateCommand command = SignedUrlCreateCommand.of(signedUrlRequestDto, userId);
        return BaseResponse.success(userQuestUseCase.getSignedUrl(command));
    }

    @Operation(
            summary = "작성된 퀘스트 상세 조회",
            description = "기록한 퀘스트를 조회하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "작성된 퀘스트 상세 조회 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "현재 진행 중인 퀘스트가 아닙니다."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @GetMapping("/answer/{questId}")
    public BaseResponse<UserQuestDetailResponseDto> getDetailQuest(
            @UserId final Long userId,
            @PathVariable final Long questId
    ){
        UserQuestDetailCommand command = UserQuestDetailCommand.of(questId, userId);

        return BaseResponse.success(userQuestUseCase.getDetailQuest(command));
    }

    @Operation(
            summary = "완료된 퀘스트 여정 조회",
            description = "완료된 여정을 조회하는 API입니다.",
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
    @GetMapping("/journey")
    public BaseResponse<JourneyListResponseDto> getCompletedJourney(
            @UserId final Long userId
    ){
        CompletedJourneyCommand command = CompletedJourneyCommand.of(userId);
        return BaseResponse.success(userQuestUseCase.getCompletedJourney(command));
    }

    @Operation(
            summary = "새로운 여정 시작하기",
            description = "새로운 여정을 눌렀을 때 보내야하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "새로운 여정 시작 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "이미 진행 중인 여정입니다."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PostMapping("/journey")
    public BaseResponse<Void> createJourney(
            @UserId final Long userId,
            @RequestParam final String journey
    ){
        JourneyUpdateCommand command = JourneyUpdateCommand.of(userId, journey);
        userQuestUseCase.updateJourneyStatus(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "퀘스트 답변 수정(질문/답변형)",
            description = "질문/답변형 퀘스트에 대한 답변을 수정하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "질문형 퀘스트 답변 수정 성공"
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
    @PatchMapping("/{questId}/recording")
    public BaseResponse<Void> updateRecordingQuest(
            @UserId final Long userId,
            @RequestBody final RecordingQuestUpdateRequestDto recordingQuestUpdateRequestDto,
            @PathVariable final Long questId){
        RecordingQuestUpdateCommand command = RecordingQuestUpdateCommand.from(recordingQuestUpdateRequestDto, questId, userId);
        userQuestUseCase.updateRecordingQuest(command);
        return BaseResponse.success(null);
    }

    @Operation(
            summary = "퀘스트 답변 수정(행동형)",
            description = "퀘스트에 대한 답변을 수정하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "행동형 퀘스트 답변 수정 성공"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "존재하지 않는 유저일때"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "퀘스트 답변 길이가 200 초과일때"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "버킷에서 이미지를 찾을 수 없을때"
                    )
            }
    )
    @PatchMapping("/{questId}/active")
    public BaseResponse<Void> updateActiveQuest(
            @UserId final Long userId,
            @RequestBody final ActiveQuestUpdateRequestDto activeQuestUpdateRequestDto,
            @PathVariable final Long questId){
        ActiveQuestUpdateCommand command = ActiveQuestUpdateCommand.from(activeQuestUpdateRequestDto, questId, userId);
        userQuestUseCase.updateActiveQuest(command);
        return BaseResponse.success(null);
    }
}
