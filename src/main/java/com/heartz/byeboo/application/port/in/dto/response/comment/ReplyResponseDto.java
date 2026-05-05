package com.heartz.byeboo.application.port.in.dto.response.comment;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommentProjection;

import java.time.LocalDateTime;

public record ReplyResponseDto(
        String content,
        Long commentId,
        LocalDateTime createdAt,
        String writer,
        String profileIcon,
        Long writerId
) {
    public static ReplyResponseDto of(UserCommentProjection userCommentProjection){
        return new ReplyResponseDto(userCommentProjection.getContent(), userCommentProjection.getCommentId(), userCommentProjection.getWrittenAt(), userCommentProjection.getWriter(), userCommentProjection.getProfileIcon().name(), userCommentProjection.getWriterId());
    }
}