package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportEntity;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.UserCommonQuestReport;
import com.heartz.byeboo.domain.type.EReportStatus;

public class UserCommonQuestReportMapper {

    public static UserCommonQuestReport toDomain(User user, UserCommonQuest userCommonQuest, UserCommonQuestReportEntity userCommonQuestReportEntity) {
        return UserCommonQuestReport.builder()
                .id(userCommonQuestReportEntity.getId())
                .user(user)
                .userCommonQuest(userCommonQuest)
                .reportStatus(userCommonQuestReportEntity.getReportStatus())
                .build();
    }

    public static UserCommonQuestReport toPendingDomain(User user, UserCommonQuest userCommonQuest) {
        return UserCommonQuestReport.builder()
                .user(user)
                .userCommonQuest(userCommonQuest)
                .reportStatus(EReportStatus.PENDING)
                .build();
    }

    public static UserCommonQuestReportEntity toEntity(UserCommonQuestReport userCommonQuestReport) {
        return UserCommonQuestReportEntity.create(
                userCommonQuestReport.getUser().getId(),
                userCommonQuestReport.getUserCommonQuest().getId(),
                userCommonQuestReport.getReportStatus()
        );
    }

    public static UserCommonQuestReportEntity toEntityForUpdate(UserCommonQuestReport userCommonQuestReport) {
        return UserCommonQuestReportEntity.from(userCommonQuestReport);
    }

}
