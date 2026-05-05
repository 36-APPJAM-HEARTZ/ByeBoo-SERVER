package com.heartz.byeboo.application.port.in.dto.response.comment;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommentProjection;

import java.time.LocalDateTime;

public record CommentResponseDto(
       String content,
       String writer,
       LocalDateTime createdAt,
       String profileIcon,
       Long writerId,
       Long commentId
) {
    public static CommentResponseDto from(UserCommentProjection userCommentProjection){
        return new CommentResponseDto(userCommentProjection.getContent(), userCommentProjection.getWriter(), userCommentProjection.getWrittenAt(), userCommentProjection.getProfileIcon().name(), userCommentProjection.getWriterId(), userCommentProjection.getCommentId());
    }
}