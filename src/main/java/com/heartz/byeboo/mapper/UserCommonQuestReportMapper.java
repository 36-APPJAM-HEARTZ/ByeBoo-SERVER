package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportsEntity;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.UserCommonQuestReport;
import com.heartz.byeboo.domain.type.EReportStatus;

public class UserCommonQuestReportMapper {

    public static UserCommonQuestReport toDomain(User user, UserCommonQuest userCommonQuest, UserCommonQuestReportsEntity userCommonQuestReportsEntity) {
        return UserCommonQuestReport.builder()
                .id(userCommonQuestReportsEntity.getId())
                .user(user)
                .userCommonQuest(userCommonQuest)
                .reportStatus(userCommonQuestReportsEntity.getReportStatus())
                .build();
    }

    public static UserCommonQuestReport toPendingDomain(User user, UserCommonQuest userCommonQuest) {
        return UserCommonQuestReport.builder()
                .user(user)
                .userCommonQuest(userCommonQuest)
                .reportStatus(EReportStatus.PENDING)
                .build();
    }

    public static UserCommonQuestReportsEntity toEntity(UserCommonQuestReport userCommonQuestReport) {
        return UserCommonQuestReportsEntity.create(
                userCommonQuestReport.getUser().getId(),
                userCommonQuestReport.getUserCommonQuest().getId(),
                userCommonQuestReport.getReportStatus()
        );
    }

    public static UserCommonQuestReportsEntity toEntityForUpdate(UserCommonQuestReport userCommonQuestReport) {
        return UserCommonQuestReportsEntity.from(userCommonQuestReport);
    }

}
