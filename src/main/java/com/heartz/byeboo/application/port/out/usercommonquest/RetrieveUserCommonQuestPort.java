package com.heartz.byeboo.application.port.out.usercommonquest;

import com.heartz.byeboo.domain.model.CommonQuest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;

public interface RetrieveUserCommonQuestPort {
    boolean isUserCommonQuestExists(User user);
    void deleteByUserIdAndId(Long userCommonQuestId, User user);
    UserCommonQuest getUserCommonQuestByUserAndId(User user, Long id);
}
