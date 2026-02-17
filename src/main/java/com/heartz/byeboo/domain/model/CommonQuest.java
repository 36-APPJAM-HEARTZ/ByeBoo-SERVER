package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.domain.type.EStep;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CommonQuest {
    private Long id;
    private String question;
    private LocalDate targetDate;

    public static CommonQuest of(Long id, String question, LocalDate targetDate) {
        return CommonQuest.builder()
                .id(id)
                .question(question)
                .targetDate(targetDate)
                .build();
    }
}
