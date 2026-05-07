package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommonQuestInfoV2Projection;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserCommonQuestDetailResponseV2Dto(
        Long likeCount,
        Long commentCount,
        boolean isLiked,
        Long answerId,
        String writer,
        String profileIcon,
        LocalDateTime writtenAt,
        String content,
        Long writerId
) {
    public static UserCommonQuestDetailResponseV2Dto of(UserCommonQuestInfoV2Projection userCommonQuestInfoV2Projection){
        return UserCommonQuestDetailResponseV2Dto.builder()
                .answerId(userCommonQuestInfoV2Projection.getAnswerId())
                .writer(userCommonQuestInfoV2Projection.getWriter())
                .profileIcon(userCommonQuestInfoV2Projection.getProfileIcon().name())
                .writtenAt(userCommonQuestInfoV2Projection.getWrittenAt())
                .content(userCommonQuestInfoV2Projection.getContent())
                .writerId(userCommonQuestInfoV2Projection.getWriterId())
                .likeCount(userCommonQuestInfoV2Projection.getLikeCount())
                .commentCount(userCommonQuestInfoV2Projection.getCommentCount())
                .isLiked(userCommonQuestInfoV2Projection.getIsLiked())
                .build();
    }
}
