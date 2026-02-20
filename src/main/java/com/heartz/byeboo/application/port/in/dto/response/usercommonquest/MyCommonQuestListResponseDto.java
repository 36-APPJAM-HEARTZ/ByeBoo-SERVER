package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import lombok.Builder;

import java.util.List;

@Builder
public record MyCommonQuestListResponseDto(
        boolean hasNext,
        Long nextCursor,
        List<MyCommonQuestResponseDto> answers
) {
    private MyCommonQuestListResponseDto from(boolean hasNext, Long nextCursor, MyCommonQuestResponseDto myCommonQuestResponseDto){
        return MyCommonQuestListResponseDto.builder()
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .answers(answers)
                .build();
    }
}
