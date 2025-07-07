package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.domain.type.EStep;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Quest {
    private Long id;
    private EStep step;
    private Long stepNumber;
    private Long questNumber;
    private String question;
    private EJourney journey;

    public static Quest of(Long id, EStep step, Long stepNumber, Long questNumber, String question, EJourney journey) {
        return Quest.builder()
                .id(id)
                .step(step)
                .stepNumber(stepNumber)
                .questNumber(questNumber)
                .question(question)
                .build();
    }
}
