package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.QuestEntity;
import com.heartz.byeboo.domain.model.Quest;


public class QuestMapper {
    public static Quest toDomain(QuestEntity questEntity) {
        return Quest.of(
                questEntity.getId(),
                questEntity.getStep(),
                questEntity.getStepNumber(),
                questEntity.getQuestNumber(),
                questEntity.getQuestion(),
                questEntity.getJourney()
        );
    }
}
