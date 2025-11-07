package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EQuestEmotionState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_quests")
public class UserQuestEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "answer")
    private String answer;

    @Column(name = "image_key", unique = true)
    private UUID imageKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "quest_emotion_state", nullable = false, length = 50)
    private EQuestEmotionState questEmotionState;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "quest_id", nullable = false)
    private Long questId;

    @Column(name = "notified", nullable = false)
    private boolean notified = false;

    @Builder
    public UserQuestEntity(
            String answer,
            UUID imageKey,
            EQuestEmotionState questEmotionState,
            Long userId,
            Long questId,
            boolean notified
    ) {
        this.answer = answer;
        this.imageKey = imageKey;
        this.questEmotionState = questEmotionState;
        this.userId = userId;
        this.questId = questId;
        this.notified = notified;
    }

    public static UserQuestEntity create(
            String answer,
            UUID imageKey,
            EQuestEmotionState questEmotionState,
            Long userId,
            Long questId,
            boolean notified
    ) {
        return UserQuestEntity.builder()
                .answer(answer)
                .imageKey(imageKey)
                .questEmotionState(questEmotionState)
                .userId(userId)
                .questId(questId)
                .notified(notified)
                .build();
    }

    public void updateNotified(boolean notified){
        this.notified = notified;
    }
}
