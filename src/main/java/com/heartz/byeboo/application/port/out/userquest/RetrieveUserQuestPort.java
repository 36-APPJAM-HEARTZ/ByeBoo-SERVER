package com.heartz.byeboo.application.port.out.userquest;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserQuest;

import java.time.LocalDateTime;
import java.util.List;

public interface RetrieveUserQuestPort {
    UserQuest getUserQuestByUserAndQuest( User user, Quest quest);
    UserQuest getRecentUserQuestByUserAndQuest(User user, Quest quest);
    List<UserQuestEntity> findUnnotifiedQuestsBefore(LocalDateTime threshold);
}
