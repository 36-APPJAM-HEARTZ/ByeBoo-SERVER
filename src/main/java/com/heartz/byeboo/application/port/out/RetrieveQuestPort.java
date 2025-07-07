package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.Quest;

public interface RetrieveQuestPort {
    Quest findById(Long questId);
}
