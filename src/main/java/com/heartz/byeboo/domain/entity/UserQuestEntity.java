package com.heartz.byeboo.domain.entity;

import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.domain.type.EQuestEmotionState;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    public UserQuest toModel() {
        return UserQuest.builder()
                .id(id)
                .answer(answer)
                .imageUrl(imageUrl)
                .questEmotionState(questEmotionState)
                .user(user.toModel())
                .quest(quest.toModel())
                .build();
    }
}
