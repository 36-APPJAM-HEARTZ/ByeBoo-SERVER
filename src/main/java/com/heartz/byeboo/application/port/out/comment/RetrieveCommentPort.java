package com.heartz.byeboo.application.port.out.comment;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommentProjection;
import com.heartz.byeboo.domain.model.Comment;

import java.util.List;

public interface RetrieveCommentPort {
    Comment getCommentByIdAndUserId(Long commentId, Long userId);
    Comment getCommentById(Long commentId);
    List<UserCommentProjection> getCommentsByParentId(Long parentId);
    UserCommentProjection getCommentWithWriter(Long commentId);
}
