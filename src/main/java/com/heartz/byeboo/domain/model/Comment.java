package com.heartz.byeboo.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Comment {
    private Long id;
    private User user;
    private String content;
    private UserCommonQuest userCommonQuest;

    public static Comment of(Long id, User user, UserCommonQuest userCommonQuest, String content) {
        return Comment.builder()
                .id(id)
                .user(user)
                .userCommonQuest(userCommonQuest)
                .content(content)
                .build();
    }
}
