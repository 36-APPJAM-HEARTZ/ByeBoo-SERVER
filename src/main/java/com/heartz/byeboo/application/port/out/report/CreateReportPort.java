package com.heartz.byeboo.application.port.out.report;

import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuestReports;

public interface CreateReportPort {
    void createReport(UserCommonQuestReports userCommonQuestReports);
}
