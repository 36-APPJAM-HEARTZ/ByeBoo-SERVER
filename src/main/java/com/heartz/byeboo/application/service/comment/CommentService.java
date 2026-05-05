package com.heartz.byeboo.application.service.comment;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommentProjection;
import com.heartz.byeboo.application.command.comment.*;
import com.heartz.byeboo.application.port.in.dto.response.comment.CommentResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.comment.ReplyListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.comment.ReplyResponseDto;
import com.heartz.byeboo.application.port.in.usecase.CommentUseCase;
import com.heartz.byeboo.application.port.out.comment.CreateCommentPort;
import com.heartz.byeboo.application.port.out.comment.DeleteCommentPort;
import com.heartz.byeboo.application.port.out.comment.RetrieveCommentPort;
import com.heartz.byeboo.application.port.out.comment.UpdateCommentPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.domain.model.*;
import com.heartz.byeboo.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final CreateCommentPort createCommentPort;
    private final RetrieveUserCommonQuestPort retrieveUserCommonQuestPort;
    private final RetrieveCommentPort retrieveCommentPort;
    private final UpdateCommentPort updateCommentPort;
    private final DeleteCommentPort deleteCommentPort;

    @Override
    @Transactional
    public Void createComment(CommentCreateCommand command) {
        retrieveUserPort.validateUserExists(command.getUserId());
        retrieveUserCommonQuestPort.getUserCommonQuestById(command.getTargetId());
        Comment comment = CommentMapper.commandToDomain(command);

        createCommentPort.createComment(comment);

        return null;
    }

    @Override
    @Transactional
    public Void updateComment(CommentUpdateCommand command) {
        Comment comment = retrieveCommentPort.getCommentByIdAndUserId(command.getCommentId(), command.getUserId());
        comment.updateContent(command.getContent());
        updateCommentPort.updateComment(comment);

        return null;
    }

    @Override
    @Transactional
    public Void deleteComment(CommentDeleteCommand command) {
        deleteCommentPort.deleteCommentById(command.getUserId(), command.getCommentId());

        //TODO : 대댓글 삭제
        return  null;
    }

    @Override
    @Transactional
    public Void createReply(ReplyCreateCommand command) {
        Comment parentComment = retrieveCommentPort.getCommentByIdAndUserId(command.getCommentId(), command.getUserId());

        Comment comment = CommentMapper.replyToDomain(command, parentComment.getUserCommonQuestId());

        createCommentPort.createReply(comment);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ReplyListResponseDto getReply(ReplyListCommand command) {
        retrieveUserPort.validateUserExists(command.getUserId());
        UserCommentProjection parentComment = retrieveCommentPort.getCommentWithWriter(command.getCommentId());
        List<UserCommentProjection> replies = retrieveCommentPort.getCommentsByParentId(command.getCommentId());

        List<ReplyResponseDto> replyResponses = replies.stream()
                .map(ReplyResponseDto::from)
                .toList();

        return ReplyListResponseDto.of(
                replyResponses.size(),
                CommentResponseDto.from(parentComment),
                replyResponses
        );
    }
}
