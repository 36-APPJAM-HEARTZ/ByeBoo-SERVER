package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.ActiveQuestRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.request.RecordingQuestRequestDto;
import com.heartz.byeboo.application.command.ActiveQuestCreateCommand;
import com.heartz.byeboo.adapter.in.web.dto.SignedUrlRequestDto;
import com.heartz.byeboo.adapter.in.web.dto.SignedUrlResponseDto;
import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;
import com.heartz.byeboo.application.command.SignedUrlCreateCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.core.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserQuestController {

    private final QuestUseCase questUseCase;

    @PostMapping("/quests/{questId}/recording")
    public BaseResponse<Void> createRecordingQuest(
            @RequestHeader final Long userId,
            @RequestBody final RecordingQuestRequestDto recordingQuestRequestDto,
            @PathVariable final Long questId){
        RecordingQuestCreateCommand command = RecordingQuestCreateCommand.from(recordingQuestRequestDto, questId, userId);
        questUseCase.createRecordingQuest(command);
        return BaseResponse.success(null);
    }

    @PostMapping("/quests/{questId}/active")
    public BaseResponse<Void> createActiveQuest(
            @RequestHeader final Long userId,
            @RequestBody final ActiveQuestRequestDto activeQuestRequestDto,
            @PathVariable final Long questId){
        ActiveQuestCreateCommand command = ActiveQuestCreateCommand.from(activeQuestRequestDto, questId, userId);
        questUseCase.createActiveQuest(command);
        return BaseResponse.success(null);
    }

    @PostMapping("/quests/images/signed-url")
    public BaseResponse<SignedUrlResponseDto> createSignedUrl(
            @RequestHeader final Long userId,
            @RequestBody final SignedUrlRequestDto signedUrlRequestDto
            ){
        SignedUrlCreateCommand command = SignedUrlCreateCommand.of(signedUrlRequestDto, userId);
        SignedUrlResponseDto response = questUseCase.getSignedUrl(command);
        return BaseResponse.success(response);
    }
}
