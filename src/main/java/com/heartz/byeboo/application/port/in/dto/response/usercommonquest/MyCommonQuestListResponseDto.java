package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import lombok.Builder;

import java.util.List;

@Builder
public record MyCommonQuestListResponseDto(
        boolean hasNext,
        Long nextCursor,
        List<MyCommonQuestResponseDto> answers
) {
    public static MyCommonQuestListResponseDto from(boolean hasNext, Long nextCursor, List<MyCommonQuestResponseDto> myCommonQuestResponseDto){
        return MyCommonQuestListResponseDto.builder()
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .answers(myCommonQuestResponseDto)
                .build();
    }
}
