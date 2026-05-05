package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import java.util.List;

public record UserCommonQuestResponseV2Dto(
        String question,
        UserCommonQuestAnswerResponseDto answer,
        List<CommentResponseDto> comments
) {
    public static UserCommonQuestResponseV2Dto of(String question, UserCommonQuestAnswerResponseDto answer, List<CommentResponseDto> comments){
        return new UserCommonQuestResponseV2Dto(question, answer, comments);
    }
}
