package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EReportStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UserCommonQuestReports {
    private Long id;
    private EReportStatus reportStatus;
    private User user;
    private UserCommonQuest userCommonQuest;

    public static UserCommonQuestReports of(Long id, EReportStatus reportStatus, User user, UserCommonQuest userCommonQuest) {
        return UserCommonQuestReports.builder()
                .id(id)
                .reportStatus(reportStatus)
                .user(user)
                .userCommonQuest(userCommonQuest)
                .build();
    }

    public static UserCommonQuestReports fromId(Long id){
        return UserCommonQuestReports.builder()
                .id(id)
                .build();
    }
}
