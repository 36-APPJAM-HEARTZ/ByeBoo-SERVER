package com.heartz.byeboo.application.port.out.commonquest;

import com.heartz.byeboo.domain.model.CommonQuest;

public interface RetrieveCommonQuestPort {
    CommonQuest getCommonQuestById(Long commonQuestId);
}
