package com.heartz.byeboo.application.port.out.comment;


import com.heartz.byeboo.domain.model.Comment;

public interface CreateCommentPort {
    void createComment(Comment comment);
    void createReply(Comment comment);
}
