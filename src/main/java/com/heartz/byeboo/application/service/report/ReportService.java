package com.heartz.byeboo.application.service.report;

import com.heartz.byeboo.application.command.report.CommonQuestReportCreateCommand;
import com.heartz.byeboo.application.port.in.usecase.ReportUseCase;
import com.heartz.byeboo.application.port.out.report.CreateReportPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.UserCommonQuestReports;
import com.heartz.byeboo.mapper.UserCommonQuestReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService implements ReportUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveUserCommonQuestPort retrieveUserCommonQuestPort;
    private final CreateReportPort createReportPort;

    @Override
    @Transactional
    public Void reportCommonQuest(CommonQuestReportCreateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        UserCommonQuest targetUserCommonQuest = retrieveUserCommonQuestPort.getUserCommonQuestById(command.getTargetId());

        UserCommonQuestReports userCommonQuestReports = UserCommonQuestReportMapper.toPendingDomain(findUser, targetUserCommonQuest);
        createReportPort.createReport(userCommonQuestReports);

        //TODO : 신고하면 디코 알림

        return null;
    }
}
