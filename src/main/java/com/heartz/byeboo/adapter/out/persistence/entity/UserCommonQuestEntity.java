package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.model.UserQuest;
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
@Table(name = "user_common_quests")
public class UserCommonQuestEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "answer", length = 500)
    private String answer;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "common_quest_id", nullable = false)
    private Long commonQuestId;

    @Builder
    public UserCommonQuestEntity(
            Long id,
            String answer,
            Long userId,
            Long commonQuestId
    ) {
        this.id = id;
        this.answer = answer;
        this.userId = userId;
        this.commonQuestId = commonQuestId;
    }

    public static UserCommonQuestEntity create(
            String answer,
            Long userId,
            Long commonQuestId
    ) {
        return UserCommonQuestEntity.builder()
                .answer(answer)
                .userId(userId)
                .commonQuestId(commonQuestId)
                .build();
    }

    public static UserCommonQuestEntity from(UserQuest userQuest) {
        return UserCommonQuestEntity.builder()
                .id(userQuest.getId())
                .answer(userQuest.getAnswer())
                .userId(userQuest.getUser().getId())
                .commonQuestId(userQuest.getQuest().getId())
                .build();
    }
}
