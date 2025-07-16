package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.port.in.dto.response.quest.AllQuestCompletedResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.quest.AllQuestProgressResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.quest.QuestDetailResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.quest.TipListResponseDto;
import com.heartz.byeboo.application.command.quest.AllQuestCompletedCommand;
import com.heartz.byeboo.application.command.quest.AllQuestProgressCommand;
import com.heartz.byeboo.application.command.quest.QuestDetailCommand;
import com.heartz.byeboo.application.command.quest.QuestTipCommand;

public interface QuestUseCase {
    TipListResponseDto getQuestTip(QuestTipCommand command);
    AllQuestProgressResponseDto getProgressAllQuest(AllQuestProgressCommand allQuestProgressCommand);
    AllQuestCompletedResponseDto getCompletedAllQuest(AllQuestCompletedCommand allQuestCompletedCommand);
    QuestDetailResponseDto getQuestDetail(QuestDetailCommand questDetailCommand);
}
