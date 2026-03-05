package com.heartz.byeboo.application.port.out.usercommonquest;

import com.heartz.byeboo.domain.model.UserCommonQuest;

public interface DeleteUserCommonQuestPort {
    void deleteAllByUserId(Long userId);
}
