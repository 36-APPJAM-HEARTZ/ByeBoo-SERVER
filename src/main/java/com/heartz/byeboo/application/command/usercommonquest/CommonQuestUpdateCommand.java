package com.heartz.byeboo.application.command.usercommonquest;

import com.heartz.byeboo.adapter.in.web.dto.request.CommonQuestUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommonQuestUpdateCommand {
    private Long userId;
    private Long answerId;
    private String answer;

    public static CommonQuestUpdateCommand from(CommonQuestUpdateRequestDto commonQuestUpdateRequestDto, Long answerId, Long userId){
        return CommonQuestUpdateCommand.builder()
                .answerId(answerId)
                .userId(userId)
                .answer(commonQuestUpdateRequestDto.answer())
                .build();
    }
}
