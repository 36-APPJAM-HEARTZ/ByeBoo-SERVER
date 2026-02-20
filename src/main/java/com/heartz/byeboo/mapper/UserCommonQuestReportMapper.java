package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportsEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.UserCommonQuestReports;
import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.domain.type.EReportStatus;

public class UserCommonQuestReportMapper {

    public static UserCommonQuestReports toDomain(User user, UserCommonQuest userCommonQuest, UserCommonQuestReportsEntity userCommonQuestReportsEntity) {
        return UserCommonQuestReports.builder()
                .user(user)
                .userCommonQuest(userCommonQuest)
                .reportStatus(userCommonQuestReportsEntity.getReportStatus())
                .build();
    }

    public static UserCommonQuestReports toPendingDomain(User user, UserCommonQuest userCommonQuest) {
        return UserCommonQuestReports.builder()
                .user(user)
                .userCommonQuest(userCommonQuest)
                .reportStatus(EReportStatus.PENDING)
                .build();
    }

    public static UserCommonQuestReportsEntity toEntity(UserCommonQuestReports userCommonQuestReports) {
        return UserCommonQuestReportsEntity.create(
                userCommonQuestReports.getUser().getId(),
                userCommonQuestReports.getUserCommonQuest().getId(),
                userCommonQuestReports.getReportStatus()
        );
    }

}
