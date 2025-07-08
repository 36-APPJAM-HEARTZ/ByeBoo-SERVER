package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserQuest;

public interface RetrieveUserQuestPort {
    UserQuest getUserQuestByUserAndQuest( User user, Quest quest);
    UserQuest getRecentUserQuestByUserAndQuest(User user, Quest quest);
}
