package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.UserQuest;

public interface CreateUserQuestPort {
    void createUserQuest(UserQuest userQuest);
}
