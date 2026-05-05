package com.heartz.byeboo.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Comment {
    private Long id;
    private Long userId;
    private String content;
    private Long userCommonQuestId;
    private Long parentCommentId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static Comment of(Long id, Long userId, Long userCommonQuestId, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        return Comment.builder()
                .id(id)
                .userId(userId)
                .userCommonQuestId(userCommonQuestId)
                .content(content)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
    }

    public void updateContent(String content){
        this.content = content;
    }
}
