package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.model.UserCommonQuestReport;
import com.heartz.byeboo.domain.type.EReportStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_common_quest_reports")
public class UserCommonQuestReportsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status", length = 50)
    private EReportStatus reportStatus;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_common_quest_id", nullable = false)
    private Long userCommonQuestId;

    @Builder
    public UserCommonQuestReportsEntity(
            Long id,
            EReportStatus reportStatus,
            Long userId,
            Long userCommonQuestId
    ) {
        this.id = id;
        this.reportStatus = reportStatus;
        this.userId = userId;
        this.userCommonQuestId = userCommonQuestId;
    }

    public static UserCommonQuestReportsEntity create(
            Long userId,
            Long userCommonQuestId,
            EReportStatus reportStatus
    ) {
        return UserCommonQuestReportsEntity.builder()
                .userId(userId)
                .userCommonQuestId(userCommonQuestId)
                .reportStatus(reportStatus)
                .build();
    }

    public static UserCommonQuestReportsEntity from(UserCommonQuestReport userCommonQuestReport) {
        return UserCommonQuestReportsEntity.builder()
                .id(userCommonQuestReport.getId())
                .userId(userCommonQuestReport.getUser().getId())
                .userCommonQuestId(userCommonQuestReport.getUserCommonQuest().getId())
                .reportStatus(userCommonQuestReport.getReportStatus())
                .build();
    }
}
