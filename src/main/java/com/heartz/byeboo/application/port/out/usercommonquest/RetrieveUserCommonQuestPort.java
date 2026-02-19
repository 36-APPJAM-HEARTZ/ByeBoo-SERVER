package com.heartz.byeboo.application.port.out.usercommonquest;

import com.heartz.byeboo.domain.model.CommonQuest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;

import java.time.LocalDate;
import java.util.List;

public interface RetrieveUserCommonQuestPort {
    boolean isUserCommonQuestExistsToday(User user);
    void deleteByUserIdAndId(Long userCommonQuestId, User user);
    UserCommonQuest getUserCommonQuestByUserAndId(User user, Long id);
    List<UserCommonQuest> getUserCommonQuestsByCreatedDate(LocalDate targetDate, Long cursor, int limit, CommonQuest commonQuest);
    long countByCreatedDateBetween(LocalDate targetDate);
}
