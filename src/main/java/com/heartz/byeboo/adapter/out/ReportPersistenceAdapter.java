package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.ReportEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserCommonQuestReportRepository;
import com.heartz.byeboo.application.port.out.report.CreateReportPort;
import com.heartz.byeboo.application.port.out.report.DeleteReportPort;
import com.heartz.byeboo.application.port.out.report.RetrieveReportPort;
import com.heartz.byeboo.application.port.out.report.UpdateReportPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.ReportErrorCode;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.Report;
import com.heartz.byeboo.domain.type.EReportTargetType;
import com.heartz.byeboo.mapper.UserCommonQuestReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportPersistenceAdapter implements CreateReportPort, RetrieveReportPort, UpdateReportPort, DeleteReportPort {

    private final UserCommonQuestReportRepository userCommonQuestReportRepository;

    @Override
    public ReportEntity createReport(Report report) {
        ReportEntity reportEntity = UserCommonQuestReportMapper.toEntity(report);
        return userCommonQuestReportRepository.save(reportEntity);
    }

    @Override
    public Report getById(Long reportId) {
        ReportEntity reportEntity = userCommonQuestReportRepository.findById(reportId)
                .orElseThrow(() -> new CustomException(ReportErrorCode.REPORT_NOT_FOUND));

        return UserCommonQuestReportMapper.toDomain(reportEntity);
    }

    @Override
    public void updateReport(Report report) {
        ReportEntity reportEntity = UserCommonQuestReportMapper.toEntityForUpdate(report);
        userCommonQuestReportRepository.save(reportEntity);
    }

    @Override
    public void deleteAllByReportedUserId(Long userId) {
        userCommonQuestReportRepository.deleteAllByReportedUserId(userId);
    }
}
