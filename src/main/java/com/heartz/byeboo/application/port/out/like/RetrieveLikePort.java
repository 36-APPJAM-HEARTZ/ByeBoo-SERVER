package com.heartz.byeboo.application.port.out.like;

public interface RetrieveLikePort {

    boolean existsByUserIdAndUserCommonQuestId(Long userId, Long userCommonQuestId);

    int countByUserCommonQuestId(Long userCommonQuestId);
}
