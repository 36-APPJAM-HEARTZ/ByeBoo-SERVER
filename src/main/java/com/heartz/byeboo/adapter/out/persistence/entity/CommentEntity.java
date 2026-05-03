package com.heartz.byeboo.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_common_quest_id", nullable = false)
    private Long userCommonQuestId;

    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    @Builder
    public CommentEntity(String content, Long userId, Long userCommonQuestId, Long id, Long parentCommentId) {
        this.userId = userId;
        this.content = content;
        this.userCommonQuestId = userCommonQuestId;
        this.id = id;
        this.parentCommentId = parentCommentId;
    }

    public static CommentEntity createCommentEntity(
            String content,
            Long userId,
            Long userCommonQuestId
    ) {
        return CommentEntity.builder()
                .content(content)
                .userId(userId)
                .userCommonQuestId(userCommonQuestId)
                .parentCommentId(null)
                .build();
    }

    public static CommentEntity createReplyEntity(
            String content,
            Long userId,
            Long userCommonQuestId,
            Long parentCommentId
    ) {
        return CommentEntity.builder()
                .content(content)
                .userId(userId)
                .userCommonQuestId(userCommonQuestId)
                .parentCommentId(parentCommentId)
                .build();
    }

}
