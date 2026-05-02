package com.heartz.byeboo.application.service.comment;

import com.heartz.byeboo.application.command.comment.CommentCreateCommand;
import com.heartz.byeboo.application.command.comment.CommentUpdateCommand;
import com.heartz.byeboo.application.port.in.usecase.CommentUseCase;
import com.heartz.byeboo.application.port.out.comment.CreateCommentPort;
import com.heartz.byeboo.application.port.out.comment.RetrieveCommentPort;
import com.heartz.byeboo.application.port.out.comment.UpdateCommentPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.domain.model.*;
import com.heartz.byeboo.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final CreateCommentPort createCommentPort;
    private final RetrieveUserCommonQuestPort retrieveUserCommonQuestPort;
    private final RetrieveCommentPort retrieveCommentPort;
    private final UpdateCommentPort updateCommentPort;

    @Override
    @Transactional
    public Void createComment(CommentCreateCommand command) {
        retrieveUserPort.getUserById(command.getUserId());
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
}
