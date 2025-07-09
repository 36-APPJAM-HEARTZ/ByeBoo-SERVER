package com.heartz.byeboo.application.service;

import com.heartz.byeboo.adapter.in.web.dto.response.*;
import com.heartz.byeboo.application.command.AllQuestCommand;
import com.heartz.byeboo.application.command.QuestTipCommand;
import com.heartz.byeboo.application.port.in.QuestUseCase;
import com.heartz.byeboo.application.port.out.RetrieveQuestPort;
import com.heartz.byeboo.application.port.out.RetrieveTipPort;
import com.heartz.byeboo.application.port.out.RetrieveUserJourneyPort;
import com.heartz.byeboo.application.port.out.RetrieveUserPort;
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
    public AllQuestResponseDto getAllQuest(AllQuestCommand allQuestCommand) {
        User currentUser = retrieveUserPort.getUserById(allQuestCommand.getUserId());
        UserJourney userJourney = retrieveUserJourneyPort.getUserJourneyByUserAndJourney(
                currentUser,
                allQuestCommand.getJourney()
        );
        List<Quest> quests = retrieveQuestPort.getALlQuestByJourney(allQuestCommand.getJourney());
        Map<EStep, List<Quest>> stepGroupQuests = geteStepGroupQuest(quests);
        List<StepResponseDto> stepResponses = getStepResponseByMap(stepGroupQuests);

        if(userJourney.getJourneyStatus().equals(EJourneyStatus.COMPLETED))
            return AllQuestResponseDto.of(
                    userJourney.getJourneyStart().toString() + " ~ " + userJourney.getJourneyEnd().toString(),
                    null,
                    true,
                    stepResponses
            );

        return AllQuestResponseDto.of(
                Long.toString(getProgressPeriod(userJourney)),
                currentUser.getCurrentNumber(),
                false,
                stepResponses
        );
    }

    private Long getProgressPeriod(UserJourney userJourney) {
        return LocalDate.now().toEpochDay() - userJourney.getJourneyStart().toEpochDay() + 1;
    }

    private Map<EStep, List<Quest>> geteStepGroupQuest(List<Quest> quests) {
        return quests.stream()
                .collect(Collectors.groupingBy(Quest::getStep));
    }

    private List<StepResponseDto> getStepResponseByMap(Map<EStep, List<Quest>> stepGroupQuests) {
        return stepGroupQuests.entrySet().stream()
                .sorted(Comparator.comparingInt(
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
