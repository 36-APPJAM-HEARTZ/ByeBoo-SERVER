package com.heartz.byeboo.adapter.out.persistence.repository.projection;

import java.time.LocalDateTime;

public interface MyCommonQuestV2Projection {
    Long getAnswerId();
    LocalDateTime getWrittenAt();
    String getContent();
    String getQuestion();
    Long getLikeCount();
    Long getCommentCount();
    boolean getIsLiked();
}
