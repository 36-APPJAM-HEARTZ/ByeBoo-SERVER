package com.heartz.byeboo.application.service;

import com.heartz.byeboo.adapter.in.web.dto.response.TipListResponseDto;
import com.heartz.byeboo.adapter.in.web.dto.response.TipResponseDto;
import com.heartz.byeboo.application.command.QuestTipCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.application.port.out.RetrieveQuestPort;
import com.heartz.byeboo.application.port.out.RetrieveTipPort;
import com.heartz.byeboo.domain.model.Quest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestService implements QuestUseCase {

    private final RetrieveTipPort retrieveTipPort;
    private final RetrieveQuestPort retrieveQuestPort;

    @Override
    @Transactional(readOnly = true)
    public TipListResponseDto getQuestTip(QuestTipCommand command) {
        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());
        List<TipResponseDto> tips = retrieveTipPort.getTipsByQuestId(command.getQuestId(), findQuest)
                .stream().map(TipResponseDto::from).toList();

        return TipListResponseDto.of(findQuest, tips);
    }
}
