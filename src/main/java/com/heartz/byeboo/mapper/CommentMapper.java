package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.CommentEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.application.command.comment.CommentCreateCommand;
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

    public static CommentEntity toEntity(Comment comment) {
        return CommentEntity.createCommentEntity(
                comment.getContent(),
                comment.getUserId(),
                comment.getUserCommonQuestId()
        );
    }

    public static Comment toDomain(CommentEntity commentEntity){
        return Comment.of(
                commentEntity.getId(),
                commentEntity.getUserId(),
                commentEntity.getUserCommonQuestId(),
                commentEntity.getContent()
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
