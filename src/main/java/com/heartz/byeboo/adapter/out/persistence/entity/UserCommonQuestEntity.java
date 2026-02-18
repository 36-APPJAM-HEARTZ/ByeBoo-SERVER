package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.model.UserCommonQuest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static UserCommonQuestEntity from(UserCommonQuest userCommonQuest) {
        return UserCommonQuestEntity.builder()
                .id(userCommonQuest.getId())
                .answer(userCommonQuest.getAnswer())
                .userId(userCommonQuest.getUser().getId())
                .commonQuestId(userCommonQuest.getCommonQuest().getId())
                .build();
    }
}
