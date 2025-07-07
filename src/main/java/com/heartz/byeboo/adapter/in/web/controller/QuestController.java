package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.RecordingQuestRequestDto;
import com.heartz.byeboo.application.command.RecordingQuestCreateCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.core.common.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QuestController {

    QuestUseCase questUseCase;

    @PostMapping("/quests/{questId}/recording")
    public BaseResponse<Void> createRecordingQuest(
            @Valid @RequestBody final RecordingQuestRequestDto recordingQuestRequestDto,
            @Valid @PathVariable final Long questId){
        RecordingQuestCreateCommand command = RecordingQuestCreateCommand.from(recordingQuestRequestDto);
        questUseCase.createRecordingQuest(command, questId);
        return BaseResponse.success(null);
    }
}
