package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
public class UserJourney {
    private Long id;
    private LocalDate journeyStart;
    private LocalDate journeyEnd;
    private EJourney journey;
    private EJourneyStatus journeyStatus;
    private User user;

    public static UserJourney of(
            Long id,
            LocalDate journeyStart,
            LocalDate journeyEnd,
            EJourney journey,
            EJourneyStatus journeyStatus,
            User user
    ) {
        return UserJourney.builder()
                .id(id)
                .journeyStart(journeyStart)
                .journeyEnd(journeyEnd)
                .journey(journey)
                .journeyStatus(journeyStatus)
                .user(user)
                .build();
    }

    public static List<UserJourney> initializeUserJourney(User user) {
        EJourney initialJourney = switch (user.getQuestStyle()) {
            case RECORDING -> EJourney.FACE_EMOTION;
            case ACTIVE -> EJourney.PROCESS_EMOTION;
        };

        List<UserJourney> userJourneyList = new ArrayList<>();
        userJourneyList.add(
                UserJourney.builder()
                        .journeyStart(LocalDate.now())
                        .journey(initialJourney)
                        .journeyStatus(EJourneyStatus.BEFORE_START)
                        .user(user)
                        .build()
        );


        List<EJourney> lastJourneys = Arrays.stream(EJourney.values())
                .filter(journey -> journey != initialJourney)
                .toList();

        for (EJourney journey : lastJourneys) {
            userJourneyList.add(
                    UserJourney.builder()
                            .journey(journey)
                            .journeyStatus(EJourneyStatus.NOT_COMPLETED)
                            .user(user)
                            .build());
        }

        return userJourneyList;
    }

    public void updateInitialUserJourney() {
        this.journeyStatus = EJourneyStatus.IN_PROGRESS;
    }

    public void updateUserJourneyCompleted(){
        this.journeyStatus = EJourneyStatus.COMPLETED;
        this.journeyEnd = LocalDate.now();
    }
}
