package com.heartz.byeboo.adapter.out.persistence.entity;

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
@Table(name = "tips")
public class TipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tip_step", nullable = false)
    private Integer tipStep;

    @Column(name = "tip_question", nullable = false)
    private String tipQuestion;

    @Column(name = "tip_answer", nullable = false)
    private String tipAnswer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quest_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private QuestEntity quest;

    @Builder
    public TipEntity(Integer tipStep, String tipQuestion, String tipAnswer, QuestEntity quest) {
        this.tipStep = tipStep;
        this.tipQuestion = tipQuestion;
        this.tipAnswer = tipAnswer;
        this.quest = quest;
    }

    public static TipEntity create(Integer tipStep, String tipQuestion, String tipAnswer, QuestEntity quest) {
        return TipEntity.builder()
                .tipStep(tipStep)
                .tipQuestion(tipQuestion)
                .tipAnswer(tipAnswer)
                .quest(quest)
                .build();
    }
}
