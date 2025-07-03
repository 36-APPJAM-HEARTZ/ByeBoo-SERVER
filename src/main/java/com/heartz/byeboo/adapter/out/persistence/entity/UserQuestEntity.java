package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EQuestEmotionState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quest_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private QuestEntity quest;

    @Builder
    public UserQuestEntity(String answer, String imageUrl, EQuestEmotionState questEmotionState, UserEntity user, QuestEntity quest) {
        this.answer = answer;
        this.imageUrl = imageUrl;
        this.questEmotionState = questEmotionState;
        this.user = user;
        this.quest = quest;
    }

    public static UserQuestEntity create(String answer, String imageUrl, EQuestEmotionState questEmotionState, UserEntity user, QuestEntity quest) {
        return UserQuestEntity.builder()
                .answer(answer)
                .imageUrl(imageUrl)
                .questEmotionState(questEmotionState)
                .user(user)
                .quest(quest)
                .build();
    }
}
