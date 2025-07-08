package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.type.EJourney;

public interface RetrieveQuestPort {
    Quest getQuestById(Long questId);
    Quest getQuestByJourneyAndStepNumber(EJourney journey, Long stepNumber);
}
