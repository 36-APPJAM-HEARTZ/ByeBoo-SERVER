package com.heartz.byeboo.adapter.out.persistence.repository.projection;

import java.time.LocalDate;

public interface MyCommonQuestProjection {
    Long getAnswerId();
    LocalDate getWrittenAt();
    String getContent();
    String getQuestion();
}
