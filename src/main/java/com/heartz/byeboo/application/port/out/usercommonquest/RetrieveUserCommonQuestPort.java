package com.heartz.byeboo.application.port.out.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.*;
import com.heartz.byeboo.domain.model.CommonQuest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;

import java.time.LocalDate;
import java.util.List;

public interface RetrieveUserCommonQuestPort {
    boolean isUserCommonQuestExistsToday(User user);
    void deleteByUserIdAndId(Long userCommonQuestId, User user);
    UserCommonQuest getUserCommonQuestByUserAndId(User user, Long id);
    List<UserCommonQuestInfoProjection> getUserCommonQuestsByCreatedDate(LocalDate targetDate, Long cursor, int limit, Long userId);
    long countByCreatedDateBetween(LocalDate targetDate, Long userId);
    UserCommonQuest getUserCommonQuestById(Long id);
    List<MyCommonQuestProjection> getMyCommonQuestsByUserId(User user, Long cursor, int limit);
    UserCommonQuestDetailProjection getUserCommonQuestWithWriter(Long userCommonQuestId);
    List<UserCommonQuestInfoV2Projection> getUserCommonQuestsByCreatedDateV2(LocalDate targetDate, Long cursor, int limit, Long userId);
    List<MyCommonQuestV2Projection> getMyCommonQuestsByUserIdV2(User user, Long cursor, int limit);
}
