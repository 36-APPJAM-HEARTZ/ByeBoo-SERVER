package com.heartz.byeboo.application.port.in.usecase;


import com.heartz.byeboo.application.command.comment.CommentCreateCommand;

public interface CommentUseCase {
    Void createComment(CommentCreateCommand command);
}
