package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EQuestEmotionState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_quests")
public class UserQuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "quest_emotion_state", nullable = false, length = 50)
    private EQuestEmotionState questEmotionState;

    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    @JoinColumn(name = "quest_id", nullable = false)
    private Long questId;

    @Builder
    public UserQuestEntity(
            String answer,
            String imageUrl,
            EQuestEmotionState questEmotionState,
            Long userId,
            Long questId
    ) {
        this.answer = answer;
        this.imageUrl = imageUrl;
        this.questEmotionState = questEmotionState;
        this.userId = userId;
        this.questId = questId;
    }

    public static UserQuestEntity create(
            String answer,
            String imageUrl,
            EQuestEmotionState questEmotionState,
            Long userId,
            Long questId
    ) {
        return UserQuestEntity.builder()
                .answer(answer)
                .imageUrl(imageUrl)
                .questEmotionState(questEmotionState)
                .userId(userId)
                .questId(questId)
                .build();
    }
}
