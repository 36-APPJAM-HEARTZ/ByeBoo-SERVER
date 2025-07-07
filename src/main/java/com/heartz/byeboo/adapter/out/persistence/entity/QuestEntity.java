package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EJourney;
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
public class QuestEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "step", nullable = false, length = 50)
    private EStep step;

    @Column(name = "step_number", nullable = false)
    private Long stepNumber;

    @Column(name = "quest_number", nullable = false)
    private Long questNumber;

    @Column(name = "question", nullable = false)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "journey", nullable = false, length = 50)
    private EJourney journey;

    @Builder
    public QuestEntity(EStep step, Long stepNumber, Long questNumber, String question, EJourney journey) {
        this.step = step;
        this.stepNumber = stepNumber;
        this.questNumber = questNumber;
        this.question = question;
        this.journey = journey;
    }

    public static QuestEntity create(EStep step, Long stepNumber, Long questNumber, String question, EJourney journey) {
        return QuestEntity.builder()
                .step(step)
                .stepNumber(stepNumber)
                .questNumber(questNumber)
                .question(question)
                .journey(journey)
                .build();
    }
}
