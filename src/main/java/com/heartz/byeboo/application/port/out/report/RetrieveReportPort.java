package com.heartz.byeboo.application.port.out.report;

import com.heartz.byeboo.domain.model.UserCommonQuestReports;

public interface RetrieveReportPort {
    UserCommonQuestReports getById(Long reportId);
}
