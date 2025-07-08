package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.*;
import com.heartz.byeboo.application.command.ActiveQuestCreateCommand;
import com.heartz.byeboo.application.command.QuestDetailCommand;
import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;
import com.heartz.byeboo.application.command.SignedUrlCreateCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.core.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quests")
public class UserQuestController {

    private final QuestUseCase questUseCase;

    @PostMapping("/{questId}/recording")
    public BaseResponse<Void> createRecordingQuest(
            @RequestHeader final Long userId,
            @RequestBody final RecordingQuestRequestDto recordingQuestRequestDto,
            @PathVariable final Long questId){
        RecordingQuestCreateCommand command = RecordingQuestCreateCommand.from(recordingQuestRequestDto, questId, userId);
        questUseCase.createRecordingQuest(command);
        return BaseResponse.success(null);
    }

    @PostMapping("/{questId}/active")
    public BaseResponse<Void> createActiveQuest(
            @RequestHeader final Long userId,
            @RequestBody final ActiveQuestRequestDto activeQuestRequestDto,
            @PathVariable final Long questId){
        ActiveQuestCreateCommand command = ActiveQuestCreateCommand.from(activeQuestRequestDto, questId, userId);
        questUseCase.createActiveQuest(command);
        return BaseResponse.success(null);
    }

    @PostMapping("/images/signed-url")
    public BaseResponse<SignedUrlResponseDto> createSignedUrl(
            @RequestHeader final Long userId,
            @RequestBody final SignedUrlRequestDto signedUrlRequestDto
            ){
        SignedUrlCreateCommand command = SignedUrlCreateCommand.of(signedUrlRequestDto, userId);
        SignedUrlResponseDto response = questUseCase.getSignedUrl(command);
        return BaseResponse.success(response);
    }

    @GetMapping("/{questId}")
    public BaseResponse<QuestDetailResponseDto> getDetailQuest(
            @RequestHeader final Long userId,
            @PathVariable final Long questId
    ){
        QuestDetailCommand command = QuestDetailCommand.of(questId, userId);
        QuestDetailResponseDto response =
    }
}
