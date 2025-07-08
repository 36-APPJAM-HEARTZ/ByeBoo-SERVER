package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.UserQuest;

public interface RetrieveUserQuestPort {
    UserQuest getUserQuestByUserId(Long userId);
}
