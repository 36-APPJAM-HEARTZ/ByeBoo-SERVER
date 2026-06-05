package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.MyCommonQuestProjection;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.MyCommonQuestV2Projection;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MyCommonQuestResponseV2Dto(
    Long answerId,
    LocalDateTime writtenAt,
    String content,
    String question,
    Long likeCount,
    Long commentCount,
    boolean isLiked
) {
    public static MyCommonQuestResponseV2Dto of(MyCommonQuestV2Projection myCommonQuestProjection){
        return MyCommonQuestResponseV2Dto.builder()
                .answerId(myCommonQuestProjection.getAnswerId())
                .content(myCommonQuestProjection.getContent())
                .writtenAt(myCommonQuestProjection.getWrittenAt())
                .question(myCommonQuestProjection.getQuestion())
                .likeCount(myCommonQuestProjection.getLikeCount())
                .commentCount(myCommonQuestProjection.getCommentCount())
                .isLiked(myCommonQuestProjection.getIsLiked())
                .build();
    }
}
