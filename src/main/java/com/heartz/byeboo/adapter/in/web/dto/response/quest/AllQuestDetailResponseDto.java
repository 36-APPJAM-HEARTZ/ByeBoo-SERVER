package com.heartz.byeboo.adapter.in.web.dto.response.quest;

import com.heartz.byeboo.domain.model.Quest;

public record AllQuestDetailResponseDto(
        Long questId,
        Long questNumber
) {
    public static AllQuestDetailResponseDto from(Quest quest){
        return new AllQuestDetailResponseDto(quest.getId(), quest.getQuestNumber());
    }
}
