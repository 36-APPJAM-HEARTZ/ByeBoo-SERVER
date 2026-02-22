package com.heartz.byeboo.application.port.out.report;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportEntity;
import com.heartz.byeboo.domain.model.UserCommonQuestReport;

public interface CreateReportPort {
    UserCommonQuestReportEntity createReport(UserCommonQuestReport userCommonQuestReport);
}
