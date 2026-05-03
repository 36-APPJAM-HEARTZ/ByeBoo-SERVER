package com.heartz.byeboo.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "likes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_likes_user_common_quest",
                        columnNames = {"user_id", "user_common_quest_id"}
                )
        }
)
public class LikeEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_common_quest_id", nullable = false)
    private Long userCommonQuestId;

    @Builder
    public LikeEntity(Long id, Long userId, Long userCommonQuestId) {
        this.id = id;
        this.userId = userId;
        this.userCommonQuestId = userCommonQuestId;
    }

    public static LikeEntity create(
            Long userId,
            Long userCommonQuestId
    ) {
        return LikeEntity.builder()
                .userId(userId)
                .userCommonQuestId(userCommonQuestId)
                .build();
    }

}
