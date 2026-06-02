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
    private Integer sequence;

    public static CommonQuest of(Long id, String question, Integer sequence) {
        return CommonQuest.builder()
                .id(id)
                .question(question)
                .sequence(sequence)
                .build();
    }

    public static CommonQuest fromId(Long id){
        return CommonQuest.builder()
                .id(id)
                .build();
    }
}
