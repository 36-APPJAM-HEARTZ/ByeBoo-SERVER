package com.heartz.byeboo.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Comment {
    private Long id;
    private Long userId;
    private String content;
    private Long userCommonQuestId;
    private Long parentCommentId;

    public static Comment of(Long id, Long userId, Long userCommonQuestId, String content) {
        return Comment.builder()
                .id(id)
                .userId(userId)
                .userCommonQuestId(userCommonQuestId)
                .content(content)
                .build();
    }

    public void updateContent(String content){
        this.content = content;
    }
}
