package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.response.quest.AllQuestResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.quest.QuestDetailResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.quest.TipListResponseDto;
import com.heartz.byeboo.application.command.quest.AllQuestCommand;
import com.heartz.byeboo.application.command.quest.QuestDetailCommand;
import com.heartz.byeboo.application.command.quest.QuestTipCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.core.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quests")
public class QuestController {

    private final QuestUseCase questUseCase;

    @GetMapping("/{questId}/tip")
    public BaseResponse<TipListResponseDto> getQuestTip(
            @RequestHeader final Long userId,
            @PathVariable final Long questId
    ){
        QuestTipCommand command = QuestTipCommand.of(userId, questId);
        return BaseResponse.success( questUseCase.getQuestTip(command));
    }

    @GetMapping("/all")
    public BaseResponse<AllQuestResponseDto> getAllQuest(
            @RequestHeader final Long userId,
            @RequestParam final String journey
    ){
        AllQuestCommand allQuestCommand = AllQuestCommand.of(userId, journey);
        return BaseResponse.success(questUseCase.getAllQuest(allQuestCommand));
    }

    @GetMapping("/{questId}")
    public BaseResponse<QuestDetailResponseDto> getQuestDetail(
            @RequestHeader final Long userId,
            @PathVariable final Long questId
    ){
        QuestDetailCommand questDetailCommand = QuestDetailCommand.of(userId, questId);
        return BaseResponse.success(questUseCase.getQuestDetail(questDetailCommand));
    }
}
