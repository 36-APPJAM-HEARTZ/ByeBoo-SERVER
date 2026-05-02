package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.CommentEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.application.command.comment.CommentCreateCommand;
import com.heartz.byeboo.domain.model.Comment;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;


public class CommentMapper {
    public static Comment commandToDomain(CommentCreateCommand command, User user, UserCommonQuest userCommonQuest) {
        return Comment.builder()
                .content(command.getContent())
                .user(user)
                .userCommonQuest(userCommonQuest)
                .build();
    }

    public static CommentEntity toEntity(Comment comment) {
        return CommentEntity.create(
                comment.getContent(),
                comment.getUser().getId(),
                comment.getUserCommonQuest().getId()
        );
    }
}
