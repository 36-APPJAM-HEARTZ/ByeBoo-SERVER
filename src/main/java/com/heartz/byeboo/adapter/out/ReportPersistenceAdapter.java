package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportsEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserCommonQuestReportRepository;
import com.heartz.byeboo.application.port.out.report.CreateReportPort;
import com.heartz.byeboo.domain.model.UserCommonQuestReports;
import com.heartz.byeboo.mapper.UserCommonQuestReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportPersistenceAdapter implements CreateReportPort {

    private final UserCommonQuestReportRepository userCommonQuestReportRepository;

    @Override
    public void createReport(UserCommonQuestReports userCommonQuestReports) {
        UserCommonQuestReportsEntity userCommonQuestReportsEntity = UserCommonQuestReportMapper.toEntity(userCommonQuestReports);
        userCommonQuestReportRepository.save(userCommonQuestReportsEntity);
    }
}
