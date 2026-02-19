package com.heartz.byeboo.application.port.out.commonquest;

import com.heartz.byeboo.domain.model.CommonQuest;

import java.time.LocalDate;

public interface RetrieveCommonQuestPort {
    CommonQuest getCommonQuestById(Long commonQuestId);
    CommonQuest getCommonQuestByTargetDate(LocalDate targetDate);
}
