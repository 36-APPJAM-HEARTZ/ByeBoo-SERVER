package com.heartz.byeboo.application.port.in;

import com.heartz.byeboo.adapter.in.web.dto.response.quest.AllQuestResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.quest.QuestDetailResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.quest.TipListResponseDto;
import com.heartz.byeboo.application.command.quest.AllQuestCommand;
import com.heartz.byeboo.application.command.quest.QuestDetailCommand;
import com.heartz.byeboo.application.command.quest.QuestTipCommand;

public interface QuestUseCase {
    TipListResponseDto getQuestTip(QuestTipCommand command);
    AllQuestResponseDto getAllQuest(AllQuestCommand allQuestCommand);
    QuestDetailResponseDto getQuestDetail(QuestDetailCommand questDetailCommand);
}
