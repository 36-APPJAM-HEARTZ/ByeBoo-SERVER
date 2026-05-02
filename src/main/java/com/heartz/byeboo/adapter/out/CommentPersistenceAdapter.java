package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.CommentEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.CommentRepository;
import com.heartz.byeboo.application.port.out.comment.CreateCommentPort;
import com.heartz.byeboo.domain.model.Comment;
import com.heartz.byeboo.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CommentPersistenceAdapter implements CreateCommentPort {

    private final CommentRepository commentRepository;

    @Override
    public void createComment(Comment comment) {
        CommentEntity commentEntity = CommentMapper.toEntity(comment);
        commentRepository.save(commentEntity);
    }
}
