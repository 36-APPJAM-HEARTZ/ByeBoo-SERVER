package com.heartz.byeboo.application.port.out.quest;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.Tip;

import java.util.List;

public interface RetrieveTipPort {
    List<Tip> getTipsByQuestId(Long questId, Quest quest);
}
