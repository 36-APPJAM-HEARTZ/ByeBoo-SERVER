package com.heartz.byeboo.application.port.out.report;

import com.heartz.byeboo.adapter.out.persistence.entity.ReportEntity;
import com.heartz.byeboo.domain.model.Report;

public interface CreateReportPort {
    ReportEntity createReport(Report report);
}
