package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.CommentEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.application.command.comment.CommentCreateCommand;
import com.heartz.byeboo.application.command.comment.ReplyCreateCommand;
import com.heartz.byeboo.domain.model.Comment;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;


public class CommentMapper {
    public static Comment commandToDomain(CommentCreateCommand command) {
        return Comment.builder()
                .content(command.getContent())
                .userId(command.getUserId())
                .userCommonQuestId(command.getTargetId())
                .build();
    }

    public static Comment replyToDomain(ReplyCreateCommand command, Long userCommonQuestId) {
        return Comment.builder()
                .content(command.getContent())
                .userId(command.getUserId())
                .parentCommentId(command.getCommentId())
                .userCommonQuestId(userCommonQuestId)
                .build();
    }

    public static CommentEntity toCommentEntity(Comment comment) {
        return CommentEntity.createCommentEntity(
                comment.getContent(),
                comment.getUserId(),
                comment.getUserCommonQuestId()
        );
    }

    public static CommentEntity toReplyEntity(Comment comment) {
        return CommentEntity.createReplyEntity(
                comment.getContent(),
                comment.getUserId(),
                comment.getUserCommonQuestId(),
                comment.getParentCommentId()
        );
    }

    public static Comment toDomain(CommentEntity commentEntity){
        return Comment.of(
                commentEntity.getId(),
                commentEntity.getUserId(),
                commentEntity.getUserCommonQuestId(),
                commentEntity.getContent(),
                commentEntity.getCreatedDate(),
                commentEntity.getModifiedDate()
        );
    }

    public static CommentEntity toEntityForUpdate(Comment comment) {
        return CommentEntity.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .userCommonQuestId(comment.getUserCommonQuestId())
                .content(comment.getContent())
                .build();
    }
}
