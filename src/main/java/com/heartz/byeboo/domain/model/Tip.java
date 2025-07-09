package com.heartz.byeboo.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tip {
    private Long id;
    private Integer tipStep;
    private String tipQuestion;
    private String tipAnswer;
    private Quest quest;

    public static Tip of(Long id, Integer tipStep, String tipQuestion, String tipAnswer, Quest quest){
        return Tip.builder()
                .id(id)
                .tipStep(tipStep)
                .tipQuestion(tipQuestion)
                .tipAnswer(tipAnswer)
                .quest(quest)
                .build();
    }
}
