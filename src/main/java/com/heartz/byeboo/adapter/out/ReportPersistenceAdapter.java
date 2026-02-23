package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserCommonQuestReportRepository;
import com.heartz.byeboo.application.port.out.report.CreateReportPort;
import com.heartz.byeboo.application.port.out.report.RetrieveReportPort;
import com.heartz.byeboo.application.port.out.report.UpdateReportPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.ReportErrorCode;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.UserCommonQuestReport;
import com.heartz.byeboo.mapper.UserCommonQuestReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportPersistenceAdapter implements CreateReportPort, RetrieveReportPort, UpdateReportPort {

    private final UserCommonQuestReportRepository userCommonQuestReportRepository;

    @Override
    public UserCommonQuestReportEntity createReport(UserCommonQuestReport userCommonQuestReport) {
        UserCommonQuestReportEntity userCommonQuestReportEntity = UserCommonQuestReportMapper.toEntity(userCommonQuestReport);
        return userCommonQuestReportRepository.save(userCommonQuestReportEntity);
    }

    @Override
    public UserCommonQuestReport getById(Long reportId) {
        UserCommonQuestReportEntity userCommonQuestReportEntity = userCommonQuestReportRepository.findById(reportId)
                .orElseThrow(() -> new CustomException( ReportErrorCode.REPORT_NOT_FOUND));

        UserCommonQuest referenceUserCommonQuest = UserCommonQuest.fromId(userCommonQuestReportEntity.getUserCommonQuestId());
        User referenceUser = User.fromId(userCommonQuestReportEntity.getUserId());

        return UserCommonQuestReportMapper.toDomain(referenceUser, referenceUserCommonQuest, userCommonQuestReportEntity);
    }

    @Override
    public void updateReport(UserCommonQuestReport userCommonQuestReport) {
        UserCommonQuestReportEntity userCommonQuestReportEntity = UserCommonQuestReportMapper.toEntityForUpdate(userCommonQuestReport);
        userCommonQuestReportRepository.save(userCommonQuestReportEntity);
    }
}
