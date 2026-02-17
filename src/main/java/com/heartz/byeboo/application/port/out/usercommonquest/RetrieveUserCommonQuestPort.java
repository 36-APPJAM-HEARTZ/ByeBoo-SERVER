package com.heartz.byeboo.application.port.out.usercommonquest;

import com.heartz.byeboo.domain.model.User;

public interface RetrieveUserCommonQuestPort {
    boolean isUserCommonQuestExists(User user);
}
