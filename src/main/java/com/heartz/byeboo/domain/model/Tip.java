package com.heartz.byeboo.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tip {
    private Integer tipStep;
    private String tipQuestion;
    private String tipAnswer;
    private Quest quest;
}
