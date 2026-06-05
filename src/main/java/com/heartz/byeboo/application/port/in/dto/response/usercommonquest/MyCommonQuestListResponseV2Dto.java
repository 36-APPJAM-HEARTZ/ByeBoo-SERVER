package com.heartz.byeboo.application.port.in.dto.response.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.MyCommonQuestProjection;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MyCommonQuestListResponseV2Dto(
        boolean hasNext,
        Long nextCursor,
        List<MyCommonQuestResponseV2Dto> answers
) {
    public static MyCommonQuestListResponseV2Dto from(boolean hasNext, Long nextCursor, List<MyCommonQuestResponseV2Dto> myCommonQuestResponseDto){
        return MyCommonQuestListResponseV2Dto.builder()
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .answers(myCommonQuestResponseDto)
                .build();
    }
}
