package com.heartz.byeboo.application.port.out.comment;

public interface DeleteCommentPort {
    void deleteCommentById(Long userId, Long commentId);
    void deleteAllByUserId(Long userId);
    void deleteAllByUserCommonQuestId(Long userCommonQuestId);
    void deleteAllReplyByParentId(Long parentCommentId);
}
