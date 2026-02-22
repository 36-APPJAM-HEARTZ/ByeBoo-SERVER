package com.heartz.byeboo.application.command.report;

import com.heartz.byeboo.adapter.in.web.dto.request.ReportStatusUpdateRequestDto;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.ReportErrorCode;
import com.heartz.byeboo.domain.type.EReportStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReportUpdateCommand {
    Long reportId;
    EReportStatus reportStatus;

    public static ReportUpdateCommand from(Long reportId, ReportStatusUpdateRequestDto reportStatusUpdateRequestDto){
        try {
            return builder()
                    .reportId(reportId)
                    .reportStatus(EReportStatus.valueOf(reportStatusUpdateRequestDto.reportStatus()))
                    .build();
        } catch (IllegalArgumentException e){
            throw new CustomException(ReportErrorCode.INVALID_REPORT_STATUS);
        }

    }
}
