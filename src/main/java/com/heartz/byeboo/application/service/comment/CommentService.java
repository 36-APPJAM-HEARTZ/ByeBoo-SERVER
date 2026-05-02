package com.heartz.byeboo.application.service.comment;

import com.heartz.byeboo.application.command.comment.CommentCreateCommand;
import com.heartz.byeboo.application.port.in.usecase.CommentUseCase;
import com.heartz.byeboo.application.port.out.comment.CreateCommentPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.domain.model.*;
import com.heartz.byeboo.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveUserCommonQuestPort retrieveUserCommonQuestPort;
    private final CreateCommentPort createCommentPort;

    @Override
    public Void createComment(CommentCreateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        UserCommonQuest findUserCommonQuest = retrieveUserCommonQuestPort.getUserCommonQuestById(command.getTargetId());
        Comment comment = CommentMapper.commandToDomain(command, findUser, findUserCommonQuest);

        createCommentPort.createComment(comment);

        return null;
    }
}
