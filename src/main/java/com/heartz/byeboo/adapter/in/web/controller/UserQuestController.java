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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quests")
public class UserQuestController {

    private final UserQuestUseCase userQuestUseCase;

    @PostMapping("/{questId}/recording")
    public BaseResponse<Void> createRecordingQuest(
            @RequestHeader final Long userId,
            @RequestBody final RecordingQuestRequestDto recordingQuestRequestDto,
            @PathVariable final Long questId){
        RecordingQuestCreateCommand command = RecordingQuestCreateCommand.from(recordingQuestRequestDto, questId, userId);
        userQuestUseCase.createRecordingQuest(command);
        return BaseResponse.success(null);
    }

    @PostMapping("/{questId}/active")
    public BaseResponse<Void> createActiveQuest(
            @RequestHeader final Long userId,
            @RequestBody final ActiveQuestRequestDto activeQuestRequestDto,
            @PathVariable final Long questId){
        ActiveQuestCreateCommand command = ActiveQuestCreateCommand.from(activeQuestRequestDto, questId, userId);
        userQuestUseCase.createActiveQuest(command);
        return BaseResponse.success(null);
    }

    @PostMapping("/images/signed-url")
    public BaseResponse<SignedUrlResponseDto> createSignedUrl(
            @RequestHeader final Long userId,
            @RequestBody final SignedUrlRequestDto signedUrlRequestDto
            ){
        SignedUrlCreateCommand command = SignedUrlCreateCommand.of(signedUrlRequestDto, userId);
        return BaseResponse.success(userQuestUseCase.getSignedUrl(command));
    }

    @GetMapping("/{questId}")
    public BaseResponse<UserQuestDetailResponseDto> getDetailQuest(
            @RequestHeader final Long userId,
            @PathVariable final Long questId
    ){
        QuestDetailCommand command = QuestDetailCommand.of(questId, userId);

        return BaseResponse.success(userQuestUseCase.getDetailQuest(command));
    }

    @GetMapping("/journey")
    public BaseResponse<JourneyListResponseDto> getCompletedJourney(
            @RequestHeader final Long userId
    ){
        CompletedJourneyCommand command = CompletedJourneyCommand.of(userId);
        return BaseResponse.success(userQuestUseCase.getCompletedJourney(command));
    }

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
