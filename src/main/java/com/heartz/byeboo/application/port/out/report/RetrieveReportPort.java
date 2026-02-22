package com.heartz.byeboo.application.port.out.report;

import com.heartz.byeboo.domain.model.UserCommonQuestReport;

public interface RetrieveReportPort {
    UserCommonQuestReport getById(Long reportId);
}
