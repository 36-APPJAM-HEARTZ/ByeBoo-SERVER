package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.model.Report;
import com.heartz.byeboo.domain.type.EReportStatus;
import com.heartz.byeboo.domain.type.EReportTargetType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reports")
public class ReportEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status", length = 50)
    private EReportStatus reportStatus;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_target_type", nullable = false)
    private EReportTargetType reportTargetType;

    @Builder
    public ReportEntity(
            Long id,
            EReportStatus reportStatus,
            Long userId,
            Long targetId,
            EReportTargetType reportTargetType

    ) {
        this.id = id;
        this.reportStatus = reportStatus;
        this.userId = userId;
        this.targetId = targetId;
        this.reportTargetType = reportTargetType;
    }

    public static ReportEntity create(
            Long userId,
            Long targetId,
            EReportStatus reportStatus,
            EReportTargetType reportTargetType
    ) {
        return ReportEntity.builder()
                .userId(userId)
                .targetId(targetId)
                .reportStatus(reportStatus)
                .reportTargetType(reportTargetType)
                .build();
    }

    public static ReportEntity from(Report report) {
        return ReportEntity.builder()
                .id(report.getId())
                .userId(report.getUserId())
                .targetId(report.getTargetId())
                .reportStatus(report.getReportStatus())
                .reportTargetType(report.getReportTargetType())
                .build();
    }
}
