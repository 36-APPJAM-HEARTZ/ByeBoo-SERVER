package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.Quest;

import java.util.List;

public interface RetrieveQuestPort {
    Quest getQuestById(Long questId);
}
