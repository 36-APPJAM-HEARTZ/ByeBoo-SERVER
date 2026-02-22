package com.heartz.byeboo.application.service.report;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportEntity;
import com.heartz.byeboo.application.command.report.CommonQuestReportCreateCommand;
import com.heartz.byeboo.application.command.report.ReportUpdateCommand;
import com.heartz.byeboo.application.port.in.usecase.ReportUseCase;
import com.heartz.byeboo.application.port.out.report.CreateReportPort;
import com.heartz.byeboo.application.port.out.report.RetrieveReportPort;
import com.heartz.byeboo.application.port.out.report.UpdateReportPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.UserCommonQuestReport;
import com.heartz.byeboo.infrastructure.api.discord.DiscordReportFeignClient;
import com.heartz.byeboo.infrastructure.dto.discord.DiscordMessageDto;
import com.heartz.byeboo.infrastructure.dto.discord.EmbedDto;
import com.heartz.byeboo.mapper.UserCommonQuestReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService implements ReportUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveUserCommonQuestPort retrieveUserCommonQuestPort;
    private final CreateReportPort createReportPort;
    private final DiscordReportFeignClient discordClient;
    private final RetrieveReportPort retrieveReportPort;
    private final UpdateReportPort updateReportPort;

    @Override
    @Transactional
    public Void reportCommonQuest(CommonQuestReportCreateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        UserCommonQuest targetUserCommonQuest = retrieveUserCommonQuestPort.getUserCommonQuestById(command.getTargetId());

        UserCommonQuestReport userCommonQuestReport = UserCommonQuestReportMapper.toPendingDomain(findUser, targetUserCommonQuest);
        UserCommonQuestReportEntity userCommonQuestReportEntity = createReportPort.createReport(userCommonQuestReport);

        //신고하면 디코 알림
        discordClient.sendAlarm(DiscordMessageDto.report(List.of(EmbedDto.reportNotification(userCommonQuestReportEntity.getId(), findUser.getId(), command.getTargetId(), targetUserCommonQuest.getAnswer()))));

        return null;
    }

    @Override
    public Void updateReportStatus(ReportUpdateCommand command) {
        UserCommonQuestReport targetUserCommonQuestReport = retrieveReportPort.getById(command.getReportId());
        targetUserCommonQuestReport.updateReportStatus(command.getReportStatus());

        updateReportPort.updateReport(targetUserCommonQuestReport);
        return null;
    }
}
