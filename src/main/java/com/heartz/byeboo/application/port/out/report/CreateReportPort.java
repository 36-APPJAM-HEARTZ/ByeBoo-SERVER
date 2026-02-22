package com.heartz.byeboo.application.port.out.report;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportsEntity;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuestReports;

public interface CreateReportPort {
    UserCommonQuestReportsEntity createReport(UserCommonQuestReports userCommonQuestReports);
}
