package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.adapter.out.persistence.entity.QuestEntity;
import com.heartz.byeboo.domain.model.Quest;

public interface RetrieveQuestPort {
    Quest getQuestById(Long questId);
}
