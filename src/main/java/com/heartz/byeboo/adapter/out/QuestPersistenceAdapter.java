package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.QuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.QuestRepository;
import com.heartz.byeboo.application.port.out.RetrieveQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.mapper.QuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class QuestPersistenceAdapter implements RetrieveQuestPort {

    private final QuestRepository questRepository;

    @Override
    public Quest getQuestById(Long questId) {
        QuestEntity questEntity = questRepository.findById(questId)
                .orElseThrow(() -> new CustomException(QuestErrorCode.QUEST_NOT_FOUND));
        return QuestMapper.toDomain(questEntity);
    }

    @Override
    public Quest getQuestByJourneyAndStepNumber(EJourney journey, Long stepNumber) {
        QuestEntity questEntity = questRepository.findByJourneyAndAndStepNumber(journey, stepNumber)
                .orElseThrow(() -> new CustomException(QuestErrorCode.QUEST_NOT_FOUND));

        return QuestMapper.toDomain(questEntity);
    }
}
