package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.domain.type.EStep;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        name = "common_quests",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_target_date", columnNames = "target_date")
        }
)
public class CommonQuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "target_date", nullable = false)
    private LocalDate targetDate;

    @Builder
    public CommonQuestEntity(String question, LocalDate targetDate) {
        this.question = question;
        this.targetDate = targetDate;
    }

    public static CommonQuestEntity create(String question, LocalDate targetDate) {
        return CommonQuestEntity.builder()
                .question(question)
                .targetDate(targetDate)
                .build();
    }
}
