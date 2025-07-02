package com.heartz.byeboo.domain.entity;

import com.heartz.byeboo.domain.model.Tip;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    public Tip toModel() {
        return Tip.builder()
                .tipStep(tipStep)
                .tipQuestion(tipQuestion)
                .tipAnswer(tipAnswer)
                .quest(quest.toModel())
                .build();
    }
}
