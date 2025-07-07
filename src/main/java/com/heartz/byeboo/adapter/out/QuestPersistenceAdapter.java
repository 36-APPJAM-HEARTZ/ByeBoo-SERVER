package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.repository.QuestRepository;
import com.heartz.byeboo.application.port.out.RetrieveQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class QuestPersistenceAdapter implements RetrieveQuestPort {

    private final QuestRepository questRepository;

    @Override
    public Quest findById(Long questId) {
        return questRepository.findById(questId)
                .orElseThrow(() -> new CustomException(QuestErrorCode.QUEST_NOT_FOUND));
    }
}
