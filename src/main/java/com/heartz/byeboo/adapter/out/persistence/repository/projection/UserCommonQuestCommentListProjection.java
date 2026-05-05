package com.heartz.byeboo.adapter.out.persistence.repository.projection;

import com.heartz.byeboo.domain.type.EProfileIcon;

import java.time.LocalDateTime;

public interface UserCommonQuestCommentListProjection {
    String getWriter();
    EProfileIcon getProfileIcon();
    LocalDateTime getWrittenAt();
    String getContent();
    Long getWriterId();
    Long getReplyCount();
    Long getCommentId();
}
