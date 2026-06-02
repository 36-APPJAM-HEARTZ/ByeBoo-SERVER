package com.heartz.byeboo.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "common_quests")
public class CommonQuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(nullable = false, unique = true)
    private Integer sequence;

    @Builder
    public CommonQuestEntity(String question, Integer sequence) {
        this.question = question;
        this.sequence = sequence;
    }

    public static CommonQuestEntity create(String question, Integer sequence) {
        return CommonQuestEntity.builder()
                .question(question)
                .sequence(sequence)
                .build();
    }
}
