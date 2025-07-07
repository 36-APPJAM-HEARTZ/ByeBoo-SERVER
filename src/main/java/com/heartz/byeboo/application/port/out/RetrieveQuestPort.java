package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.adapter.out.persistence.entity.QuestEntity;

public interface RetrieveQuestPort {
    QuestEntity findById(Long questId);
}
