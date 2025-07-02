package com.heartz.byeboo.domain.entity;

import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EStep;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    @Column(name = "step_number", nullable = false)
    private Long stepNumber;

    @Column(name = "quest_number", nullable = false)
    private Long questNumber;

    @Column(name = "question", nullable = false)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "journey", nullable = false, length = 50)
    private EJourney journey;

    public Quest toModel() {
        return Quest.builder()
                .step(step)
                .stepNumber(stepNumber)
                .questNumber(questNumber)
                .question(question)
                .journey(journey)
                .build();
    }
}
