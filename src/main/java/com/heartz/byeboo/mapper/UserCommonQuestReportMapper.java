package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.ReportEntity;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.Report;
import com.heartz.byeboo.domain.type.EReportStatus;
import com.heartz.byeboo.domain.type.EReportTargetType;

public class UserCommonQuestReportMapper {

    public static Report toDomain(ReportEntity reportEntity) {
        return Report.builder()
                .id(reportEntity.getId())
                .userId(reportEntity.getUserId())
                .targetId(reportEntity.getTargetId())
                .reportStatus(reportEntity.getReportStatus())
                .reportTargetType(reportEntity.getReportTargetType())
                .build();
    }

    public static Report toPendingDomain(Long userId, Long targetId, EReportTargetType reportTargetType) {
        return Report.builder()
                .userId(userId)
                .targetId(targetId)
                .reportTargetType(reportTargetType)
                .reportStatus(EReportStatus.PENDING)
                .build();
    }

    public static ReportEntity toEntity(Report report) {
        return ReportEntity.create(
                report.getUserId(),
                report.getTargetId(),
                report.getReportStatus(),
                report.getReportTargetType()
        );
    }

    public static ReportEntity toEntityForUpdate(Report report) {
        return ReportEntity.from(report);
    }

}
