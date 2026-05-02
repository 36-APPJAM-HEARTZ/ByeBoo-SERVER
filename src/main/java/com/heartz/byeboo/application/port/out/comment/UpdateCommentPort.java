package com.heartz.byeboo.application.port.out.comment;

import com.heartz.byeboo.domain.model.Comment;

public interface UpdateCommentPort {
    void updateComment(Comment comment);
}
