package com.heartz.byeboo.application.port.out.like;

public interface DeleteLikePort {

    void deleteByUserIdAndUserCommonQuestId(Long userId, Long userCommonQuestId);
}
