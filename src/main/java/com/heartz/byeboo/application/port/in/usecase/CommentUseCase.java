package com.heartz.byeboo.application.port.in.usecase;


import com.heartz.byeboo.application.command.comment.CommentCreateCommand;
import com.heartz.byeboo.application.command.comment.CommentDeleteCommand;
import com.heartz.byeboo.application.command.comment.CommentUpdateCommand;
import com.heartz.byeboo.application.command.comment.ReplyCreateCommand;

public interface CommentUseCase {
    Void createComment(CommentCreateCommand command);
    Void updateComment(CommentUpdateCommand command);
    Void deleteComment(CommentDeleteCommand command);
    Void createReply(ReplyCreateCommand command);
}
