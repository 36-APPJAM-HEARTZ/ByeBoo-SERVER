package com.heartz.byeboo.application.port.out.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.MyCommonQuestProjection;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommonQuestInfoProjection;
import com.heartz.byeboo.domain.model.CommonQuest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;

import java.time.LocalDate;
import java.util.List;

public interface RetrieveUserCommonQuestPort {
    boolean isUserCommonQuestExistsToday(User user);
    void deleteByUserIdAndId(Long userCommonQuestId, User user);
    UserCommonQuest getUserCommonQuestByUserAndId(User user, Long id);
    List<UserCommonQuestInfoProjection> getUserCommonQuestsByCreatedDate(LocalDate targetDate, Long cursor, int limit, CommonQuest commonQuest);
    long countByCreatedDateBetween(LocalDate targetDate);
    UserCommonQuest getUserCommonQuestById(Long id);
    List<MyCommonQuestProjection> getMyCommonQuestsByUserId(User user, Long cursor, int limit);
}
