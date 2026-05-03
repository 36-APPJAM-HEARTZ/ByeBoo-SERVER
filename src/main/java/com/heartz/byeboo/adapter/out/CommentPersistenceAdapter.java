package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.CommentEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.CommonQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.CommentRepository;
import com.heartz.byeboo.application.port.out.comment.CreateCommentPort;
import com.heartz.byeboo.application.port.out.comment.DeleteCommentPort;
import com.heartz.byeboo.application.port.out.comment.RetrieveCommentPort;
import com.heartz.byeboo.application.port.out.comment.UpdateCommentPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.CommentErrorCode;
import com.heartz.byeboo.domain.exception.CommonQuestErrorCode;
import com.heartz.byeboo.domain.model.Comment;
import com.heartz.byeboo.mapper.CommentMapper;
import com.heartz.byeboo.mapper.CommonQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CommentPersistenceAdapter implements CreateCommentPort, RetrieveCommentPort, UpdateCommentPort, DeleteCommentPort {

    private final CommentRepository commentRepository;

    @Override
    public void createComment(Comment comment) {
        CommentEntity commentEntity = CommentMapper.toCommentEntity(comment);
        commentRepository.save(commentEntity);
    }

    @Override
    public void createReply(Comment comment) {
        CommentEntity commentEntity = CommentMapper.toReplyEntity(comment);
        commentRepository.save(commentEntity);
    }

    @Override
    public Comment getCommentByIdAndUserId(Long commentId, Long userId) {
        CommentEntity commonQuestEntity = commentRepository.findByUserIdAndId(userId, commentId)
                .orElseThrow(() -> new CustomException(CommentErrorCode.COMMENT_NOT_FOUND));

        return CommentMapper.toDomain(commonQuestEntity);
    }

    @Override
    public void updateComment(Comment comment) {
        CommentEntity commentEntity = CommentMapper.toEntityForUpdate(comment);

        commentRepository.save(commentEntity);
    }

    @Override
    public void deleteCommentById(Long userId, Long commentId) {
        commentRepository.deleteByUserIdAndId(userId, commentId);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        commentRepository.deleteAllByUserId(userId);
    }

    @Override
    public void deleteAllByUserCommonQuestId(Long userCommonQuestId) {
        commentRepository.deleteAllByUserCommonQuestId(userCommonQuestId);
    }

    @Override
    public void deleteAllByParentCommentId(Long parentCommentId) {
        commentRepository.deleteAllByParentCommentId(parentCommentId);
    }
}
