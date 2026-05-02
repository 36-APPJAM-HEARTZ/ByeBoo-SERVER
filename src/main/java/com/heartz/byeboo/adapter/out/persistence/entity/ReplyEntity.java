package com.heartz.byeboo.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "replies")
public class ReplyEntity extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @Builder
    public ReplyEntity(String content, Long userId, Long commentId){
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
    }

    public static ReplyEntity create(String content, Long userId, Long commentId){
        return ReplyEntity.builder()
                .content(content)
                .userId(userId)
                .commentId(commentId)
                .build();
    }
}
