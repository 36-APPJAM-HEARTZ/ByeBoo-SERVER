package com.heartz.byeboo.application.service.report;

import com.heartz.byeboo.adapter.out.persistence.entity.ReportEntity;
import com.heartz.byeboo.application.command.report.CommonQuestReportCreateCommand;
import com.heartz.byeboo.application.command.report.ReportCreateCommand;
import com.heartz.byeboo.application.command.report.ReportUpdateCommand;
import com.heartz.byeboo.application.port.in.usecase.ReportUseCase;
import com.heartz.byeboo.application.port.out.comment.RetrieveCommentPort;
import com.heartz.byeboo.application.port.out.report.CreateReportPort;
import com.heartz.byeboo.application.port.out.report.RetrieveReportPort;
import com.heartz.byeboo.application.port.out.report.UpdateReportPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.ReportErrorCode;
import com.heartz.byeboo.domain.model.Comment;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.domain.model.Report;
import com.heartz.byeboo.domain.type.EReportTargetType;
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
    private final RetrieveCommentPort retrieveCommentPort;

    @Override
    @Transactional
    public Void reportCommonQuest(CommonQuestReportCreateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        UserCommonQuest targetUserCommonQuest = retrieveUserCommonQuestPort.getUserCommonQuestById(command.getTargetId());
        validateWriterMe(targetUserCommonQuest.getUser().getId(), findUser.getId());
        Report report = UserCommonQuestReportMapper.toPendingDomain(findUser.getId(), targetUserCommonQuest.getId(), EReportTargetType.COMMON_QUEST);
        ReportEntity reportEntity = createReportPort.createReport(report);

        //신고하면 디코 알림
        discordClient.sendAlarm(DiscordMessageDto.report(List.of(EmbedDto.reportNotification(reportEntity.getId(), findUser.getId(), command.getTargetId(), targetUserCommonQuest.getAnswer(), reportEntity.getReportTargetType()))));

        return null;
    }

    @Override
    public Void updateReportStatus(ReportUpdateCommand command) {
        Report targetReport = retrieveReportPort.getById(command.getReportId());
        targetReport.updateReportStatus(command.getReportStatus());

        updateReportPort.updateReport(targetReport);
        return null;
    }

    @Override
    public Void report(ReportCreateCommand command) {
        retrieveUserPort.validateUserExists(command.getUserId());
        String targetContent = validateAndGetTargetContent(command.getReportTargetType(), command.getUserId(), command.getTargetId());
        Report report = UserCommonQuestReportMapper.toPendingDomain(command.getUserId(), command.getTargetId(), command.getReportTargetType());
        ReportEntity reportEntity = createReportPort.createReport(report);

        //신고하면 디코 알림
        discordClient.sendAlarm(DiscordMessageDto.report(List.of(EmbedDto.reportNotification(reportEntity.getId(), command.getUserId(), command.getTargetId(), targetContent, reportEntity.getReportTargetType()))));

        return null;
    }

    private void validateWriterMe(Long writerId, Long reporterId){
        if (writerId.equals(reporterId)){
            throw new CustomException(ReportErrorCode.INVALID_WRITER_ID);
        }
    }

    private String validateAndGetTargetContent(EReportTargetType reportTargetType, Long userId, Long targetId){
        if (reportTargetType == EReportTargetType.COMMON_QUEST){
            UserCommonQuest target =
                    retrieveUserCommonQuestPort.getUserCommonQuestById(targetId);

            validateWriterMe(target.getUser().getId(), userId);
            return target.getAnswer();
        } else if (reportTargetType == EReportTargetType.COMMENT){
            Comment target =
                    retrieveCommentPort.getCommentById(targetId);

            validateWriterMe(target.getUserId(), userId);
            return target.getContent();
        }
        return null;
    }
}
