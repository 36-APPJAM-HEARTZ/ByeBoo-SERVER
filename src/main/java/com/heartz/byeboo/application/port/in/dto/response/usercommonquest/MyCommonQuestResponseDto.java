package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.MyCommonQuestProjection;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record MyCommonQuestResponseDto(
        Long answerId,
        LocalDateTime writtenAt,
        String content,
        String question
) {
    public static MyCommonQuestResponseDto of(MyCommonQuestProjection myCommonQuestProjection){
        return MyCommonQuestResponseDto.builder()
                .answerId(myCommonQuestProjection.getAnswerId())
                .content(myCommonQuestProjection.getContent())
                .writtenAt(myCommonQuestProjection.getWrittenAt())
                .question(myCommonQuestProjection.getQuestion())
                .build();
    }
}
