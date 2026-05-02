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

    @Builder
    public CommentEntity(String content, Long userId, Long userCommonQuestId, Long id) {
        this.userId = userId;
        this.content = content;
        this.userCommonQuestId = userCommonQuestId;
        this.id = id;
    }

    public static CommentEntity create(String content, Long userId, Long userCommonQuestId) {
        return CommentEntity.builder()
                .content(content)
                .userId(userId)
                .userCommonQuestId(userCommonQuestId)
                .build();
    }

}
