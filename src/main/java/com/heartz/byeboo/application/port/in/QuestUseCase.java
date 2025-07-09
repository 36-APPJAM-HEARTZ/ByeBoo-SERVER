package com.heartz.byeboo.application.port.in;

import com.heartz.byeboo.adapter.in.web.dto.response.JourneyListResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.TipListResponseDto;
import com.heartz.byeboo.application.command.CompletedJourneyCommand;
import com.heartz.byeboo.application.command.QuestTipCommand;

public interface QuestUseCase {
    TipListResponseDto getQuestTip(QuestTipCommand command);
}
