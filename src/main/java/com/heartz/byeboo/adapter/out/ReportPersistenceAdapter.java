package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportsEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserCommonQuestReportRepository;
import com.heartz.byeboo.application.port.out.report.CreateReportPort;
import com.heartz.byeboo.application.port.out.report.RetrieveReportPort;
import com.heartz.byeboo.application.port.out.report.UpdateReportPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.ReportErrorCode;
import com.heartz.byeboo.domain.exception.UserCommonQuestErrorCode;
import com.heartz.byeboo.domain.model.CommonQuest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.UserCommonQuestReports;
import com.heartz.byeboo.mapper.UserCommonQuestReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportPersistenceAdapter implements CreateReportPort, RetrieveReportPort, UpdateReportPort {

    private final UserCommonQuestReportRepository userCommonQuestReportRepository;

    @Override
    public UserCommonQuestReportsEntity createReport(UserCommonQuestReports userCommonQuestReports) {
        UserCommonQuestReportsEntity userCommonQuestReportsEntity = UserCommonQuestReportMapper.toEntity(userCommonQuestReports);
        return userCommonQuestReportRepository.save(userCommonQuestReportsEntity);
    }

    @Override
    public UserCommonQuestReports getById(Long reportId) {
        UserCommonQuestReportsEntity userCommonQuestReportsEntity = userCommonQuestReportRepository.findById(reportId)
                .orElseThrow(() -> new CustomException( ReportErrorCode.REPORT_NOT_FOUND));

        UserCommonQuest referenceUserCommonQuest = UserCommonQuest.fromId(userCommonQuestReportsEntity.getUserCommonQuestId());
        User referenceUser = User.fromId(userCommonQuestReportsEntity.getUserId());

        return UserCommonQuestReportMapper.toDomain(referenceUser, referenceUserCommonQuest, userCommonQuestReportsEntity);
    }

    @Override
    public void updateReport(UserCommonQuestReports userCommonQuestReports) {
        UserCommonQuestReportsEntity userCommonQuestReportsEntity = UserCommonQuestReportMapper.toEntityForUpdate(userCommonQuestReports);
        userCommonQuestReportRepository.save(userCommonQuestReportsEntity);
    }
}
