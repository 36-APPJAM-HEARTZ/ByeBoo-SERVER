package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommentProjection;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommonQuestCommentListProjection;

import java.time.LocalDateTime;

public record CommentResponseDto(
        String content,
        Long commentId,
        Long writerId,
        String writer,
        String profileIcon,
        LocalDateTime writtenAt,
        Long replyCount
) {
    public static CommentResponseDto of(UserCommonQuestCommentListProjection userCommonQuestCommentListProjection){
        return new CommentResponseDto(userCommonQuestCommentListProjection.getContent(), userCommonQuestCommentListProjection.getCommentId(), userCommonQuestCommentListProjection.getWriterId(), userCommonQuestCommentListProjection.getWriter(), userCommonQuestCommentListProjection.getProfileIcon().name(), userCommonQuestCommentListProjection.getWrittenAt(), userCommonQuestCommentListProjection.getReplyCount());
    }
}