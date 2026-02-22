package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EReportStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCommonQuestReport {
    private Long id;
    private EReportStatus reportStatus;
    private User user;
    private UserCommonQuest userCommonQuest;

    public static UserCommonQuestReport of(Long id, EReportStatus reportStatus, User user, UserCommonQuest userCommonQuest) {
        return UserCommonQuestReport.builder()
                .id(id)
                .reportStatus(reportStatus)
                .user(user)
                .userCommonQuest(userCommonQuest)
                .build();
    }

    public void updateReportStatus(EReportStatus newStatus) {
        this.reportStatus = newStatus;
    }
}
