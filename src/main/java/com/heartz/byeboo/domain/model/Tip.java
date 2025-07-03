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
    private Long questId;
}
