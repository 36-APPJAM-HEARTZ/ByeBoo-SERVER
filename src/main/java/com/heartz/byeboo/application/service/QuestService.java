package com.heartz.byeboo.application.service;

import com.heartz.byeboo.application.command.quest.AllQuestCompletedCommand;
import com.heartz.byeboo.application.command.quest.AllQuestProgressCommand;
import com.heartz.byeboo.application.command.quest.QuestDetailCommand;
import com.heartz.byeboo.application.command.quest.QuestTipCommand;
import com.heartz.byeboo.application.port.in.usecase.QuestUseCase;
import com.heartz.byeboo.application.port.in.dto.response.StepResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.quest.*;
import com.heartz.byeboo.application.port.out.quest.RetrieveQuestPort;
import com.heartz.byeboo.application.port.out.quest.RetrieveTipPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserJourneyPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserJourneyErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import com.heartz.byeboo.domain.type.EStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestService implements QuestUseCase {

    private final RetrieveTipPort retrieveTipPort;
    private final RetrieveQuestPort retrieveQuestPort;
    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveUserJourneyPort retrieveUserJourneyPort;

    @Override
    @Transactional(readOnly = true)
    public TipListResponseDto getQuestTip(QuestTipCommand command) {
        Quest findQuest = retrieveQuestPort.getQuestById(command.getQuestId());
        List<TipResponseDto> tips = retrieveTipPort.getTipsByQuestId(command.getQuestId(), findQuest)
                .stream().map(TipResponseDto::from).toList();

        return TipListResponseDto.of(findQuest, tips);
    }

    @Override
    @Transactional(readOnly = true)
    public AllQuestProgressResponseDto getProgressAllQuest(AllQuestProgressCommand allQuestProgressCommand) {
        User currentUser = retrieveUserPort.getUserById(allQuestProgressCommand.getUserId());
        UserJourney userJourney = retrieveUserJourneyPort.getOngoingUserJourneyByUser(currentUser);

        if(userJourney.getJourneyStatus().equals(EJourneyStatus.BEFORE_START)) {
            throw  new CustomException(UserJourneyErrorCode.BEFORE_START_USER_JOURNEY);
        }

        List<Quest> quests = retrieveQuestPort.getALlQuestByJourney(userJourney.getJourney());
        Map<EStep, List<Quest>> stepGroupQuests = getStepGroupQuest(quests);
        List<StepResponseDto> stepResponses = getStepResponseByMap(stepGroupQuests);

        return AllQuestProgressResponseDto.of(
                getProgressPeriod(userJourney),
                currentUser.getCurrentNumber(),
                stepResponses
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AllQuestCompletedResponseDto getCompletedAllQuest(AllQuestCompletedCommand allQuestCompletedCommand) {
        User currentUser = retrieveUserPort.getUserById(allQuestCompletedCommand.getUserId());
        UserJourney userJourney = retrieveUserJourneyPort.getUserJourneyByUserAndJourney(
                currentUser,
                allQuestCompletedCommand.getJourney()
        );
        List<Quest> quests = retrieveQuestPort.getALlQuestByJourney(allQuestCompletedCommand.getJourney());
        Map<EStep, List<Quest>> stepGroupQuests = getStepGroupQuest(quests);
        List<StepResponseDto> stepResponses = getStepResponseByMap(stepGroupQuests);

        if(!userJourney.getJourneyStatus().equals(EJourneyStatus.COMPLETED))
            throw new CustomException(UserJourneyErrorCode.INVALID_COMPLETED_USER_JOURNEY);

        return AllQuestCompletedResponseDto.of(
                userJourney.getJourneyStart().toString() + " ~ " + userJourney.getJourneyEnd().toString(),
                null,
                stepResponses
        );
    }

    @Override
    @Transactional(readOnly = true)
    public QuestDetailResponseDto getQuestDetail(QuestDetailCommand questDetailCommand){
        retrieveUserPort.getUserById(questDetailCommand.getUserId());
        Quest currentQuest = retrieveQuestPort.getQuestById(questDetailCommand.getQuestId());

        return QuestDetailResponseDto.from(currentQuest);
    }

    private Long getProgressPeriod(UserJourney userJourney) {
        return LocalDate.now().toEpochDay() - userJourney.getJourneyStart().toEpochDay() + 1;
    }

    private Map<EStep, List<Quest>> getStepGroupQuest(List<Quest> quests) {
        return quests.stream()
                .collect(Collectors.groupingBy(Quest::getStep));
    }

    private List<StepResponseDto> getStepResponseByMap(Map<EStep, List<Quest>> stepGroupQuests) {
        return stepGroupQuests.entrySet().stream()
                .sorted(Comparator.comparingLong(
                        stepGroupQuest -> stepGroupQuest.getKey().getStepNumber())
                )
                .map(stepListEntry -> StepResponseDto.of(
                        stepListEntry.getKey().getStepNumber(),
                        stepListEntry.getKey(),
                        stepListEntry.getValue().stream()
                                .map(AllQuestDetailResponseDto::from)
                                .toList())
                )
                .toList();

    }
}
