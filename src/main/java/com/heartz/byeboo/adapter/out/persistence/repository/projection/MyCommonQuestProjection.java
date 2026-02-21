package com.heartz.byeboo.adapter.out.persistence.repository.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface MyCommonQuestProjection {
    Long getAnswerId();
    LocalDateTime getWrittenAt();
    String getContent();
    String getQuestion();
}
