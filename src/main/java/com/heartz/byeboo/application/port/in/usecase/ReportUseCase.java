package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.report.CommonQuestReportCreateCommand;

public interface ReportUseCase {
    Void reportCommonQuest(CommonQuestReportCreateCommand command);
}
