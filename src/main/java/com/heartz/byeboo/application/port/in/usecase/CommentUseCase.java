package com.heartz.byeboo.application.port.in.usecase;


import com.heartz.byeboo.application.command.comment.*;
import com.heartz.byeboo.application.port.in.dto.response.comment.ReplyListResponseDto;

public interface CommentUseCase {
    Void createComment(CommentCreateCommand command);
    Void updateComment(CommentUpdateCommand command);
    Void deleteComment(CommentDeleteCommand command);
    Void createReply(ReplyCreateCommand command);
    ReplyListResponseDto getReply(ReplyListCommand command);
}
