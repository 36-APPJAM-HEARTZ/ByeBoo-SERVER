package com.heartz.byeboo.application.command.report;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonQuestReportCreateCommand {
    Long userId;
    Long targetId;

    public static CommonQuestReportCreateCommand from(Long userId, Long targetId){
        return CommonQuestReportCreateCommand.builder()
                .targetId(targetId)
                .userId(userId)
                .build();
    }
}
