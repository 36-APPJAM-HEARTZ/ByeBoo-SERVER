package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.response.TipListResponseDto;
import com.heartz.byeboo.application.command.QuestTipCommand;
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
}
