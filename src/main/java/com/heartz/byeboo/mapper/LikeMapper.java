package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.LikeEntity;
import com.heartz.byeboo.application.command.usercommonquest.LikeCreateCommand;
import com.heartz.byeboo.domain.model.Like;

public class LikeMapper {

    public static Like commandToDomain(LikeCreateCommand command){
        return Like.builder()
                .userId(command.getUserId())
                .userCommonQuestId(command.getUserCommonQuestId())
                .build();
    }
    public static LikeEntity toEntity(Like like){
        return LikeEntity.create(
                like.getUserId(),
                like.getUserCommonQuestId()
        );
    }
}
