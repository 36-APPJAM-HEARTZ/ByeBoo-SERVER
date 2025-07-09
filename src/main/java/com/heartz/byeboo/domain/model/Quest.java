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
    private Long questNumber;
    private String question;
    private EJourney journey;
    private EQuestStyle questStyle;

    public static Quest of(Long id, EStep step, Long questNumber, String question, EJourney journey, EQuestStyle questStyle) {
        return Quest.builder()
                .id(id)
                .step(step)
                .questNumber(questNumber)
                .question(question)
                .journey(journey)
                .questStyle(questStyle)
                .build();
    }
}
