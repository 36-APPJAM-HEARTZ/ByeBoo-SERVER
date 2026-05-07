package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EReportStatus;
import com.heartz.byeboo.domain.type.EReportTargetType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Report {
    private Long id;
    private EReportStatus reportStatus;
    private Long userId;
    private Long targetId;
    private EReportTargetType reportTargetType;

    public static Report of(Long id, EReportStatus reportStatus, Long userId, Long targetId, EReportTargetType reportTargetType) {
        return Report.builder()
                .id(id)
                .reportStatus(reportStatus)
                .userId(userId)
                .targetId(targetId)
                .reportTargetType(reportTargetType)
                .build();
    }

    public void updateReportStatus(EReportStatus newStatus) {
        this.reportStatus = newStatus;
    }
}
