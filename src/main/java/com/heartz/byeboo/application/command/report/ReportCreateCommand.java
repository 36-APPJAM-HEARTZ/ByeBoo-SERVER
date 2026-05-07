package com.heartz.byeboo.application.command.report;

import com.heartz.byeboo.adapter.in.web.dto.request.ReportCreateRequestDto;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.ReportErrorCode;
import com.heartz.byeboo.domain.type.EReportTargetType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReportCreateCommand {
    private EReportTargetType reportTargetType;
    private Long userId;
    private Long targetId;

    public static ReportCreateCommand of(Long userId, ReportCreateRequestDto reportCreateRequestDto){
        try {
            return ReportCreateCommand.builder()
                    .reportTargetType(EReportTargetType.valueOf(reportCreateRequestDto.targetType()))
                    .targetId(reportCreateRequestDto.targetId())
                    .userId(userId)
                    .build();
        } catch(IllegalArgumentException e){
            throw new CustomException(ReportErrorCode.INVALID_REPORT_TYPE);
        }
    }
}
