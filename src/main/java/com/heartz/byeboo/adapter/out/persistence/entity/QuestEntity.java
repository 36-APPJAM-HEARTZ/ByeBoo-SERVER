package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.domain.type.EStep;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "quests")
public class QuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "step", nullable = false, length = 50)
    private EStep step;

    @Column(name = "quest_number", nullable = false)
    private Long questNumber;

    @Column(name = "question", nullable = false)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "journey", nullable = false, length = 50)
    private EJourney journey;

    @Enumerated(EnumType.STRING)
    @Column(name = "quest_style", nullable = false, length = 50)
    private EQuestStyle questStyle;

    @Builder
    public QuestEntity(EStep step, Long questNumber, String question, EJourney journey, EQuestStyle questStyle) {
        this.step = step;
        this.questNumber = questNumber;
        this.question = question;
        this.journey = journey;
        this.questStyle = questStyle;
    }

    public static QuestEntity create(EStep step, Long questNumber, String question, EJourney journey, EQuestStyle questStyle) {
        return QuestEntity.builder()
                .step(step)
                .questNumber(questNumber)
                .question(question)
                .journey(journey)
                .questStyle(questStyle)
                .build();
    }
}
