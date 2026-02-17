package com.heartz.byeboo.application.command.usercommonquest;

import com.heartz.byeboo.adapter.in.web.dto.request.CommonQuestCreateRequestDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommonQuestDeleteCommand {
    private Long userId;
    private Long answerId;

    public static CommonQuestDeleteCommand from(Long answerId, Long userId){
        return CommonQuestDeleteCommand.builder()
                .answerId(answerId)
                .userId(userId)
                .build();
    }
}
