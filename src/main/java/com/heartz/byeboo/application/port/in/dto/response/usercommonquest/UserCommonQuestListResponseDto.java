package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import lombok.Builder;

import java.util.List;

@Builder
public record UserCommonQuestListResponseDto(
        String question,
        boolean isAnswered,
        long answerCount,
        List<UserCommonQuestDetailResponseDto> answers,
        boolean hasNext,
        Long nextCursor
) {
    public static UserCommonQuestListResponseDto from(String question, boolean isAnswered, long answerCount, List<UserCommonQuestDetailResponseDto> answers, boolean hasNext, Long nextCursor){
        return UserCommonQuestListResponseDto.builder()
                .answerCount(answerCount)
                .answers(answers)
                .isAnswered(isAnswered)
                .question(question)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }
}