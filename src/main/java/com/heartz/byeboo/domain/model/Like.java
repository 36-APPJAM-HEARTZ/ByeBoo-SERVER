package com.heartz.byeboo.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Like {
    private Long id;
    private Long userId;
    private Long userCommonQuestId;

    public static Like of(Long id, Long userId, Long userCommonQuestId){
        return Like.builder()
                .id(id)
                .userCommonQuestId(userCommonQuestId)
                .userId(userId)
                .build();
    }
}
