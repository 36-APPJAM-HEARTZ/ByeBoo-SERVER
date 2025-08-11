package com.heartz.byeboo.application.port.out.quest;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.type.EJourney;

import java.util.List;

public interface RetrieveQuestPort {
    Quest getQuestById(Long questId);
    Quest getQuestByJourneyAndQuestNumber(EJourney journey, Long questNumber);
    List<Quest> getALlQuestByJourney(EJourney journey);
}
