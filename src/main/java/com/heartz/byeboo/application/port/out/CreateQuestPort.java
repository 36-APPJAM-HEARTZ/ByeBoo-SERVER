package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;

public interface CreateQuestPort {
    void createQuest(User user, Quest quest);
}
