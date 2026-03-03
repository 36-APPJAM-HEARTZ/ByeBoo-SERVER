package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserErrorCode;
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
            default -> EJourney.FACE_EMOTION;
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
                .filter(journey -> journey != EJourney.PREPARE_REUNION)
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

    public static List<UserJourney> initializeUserJourneyV2(User user) {
        EJourney initialJourney = switch (user.getQuestStyle()) {
            case RECORDING -> EJourney.FACE_EMOTION;   // 이별 극복
            case REUNION -> EJourney.PREPARE_REUNION;  // 재회 준비
            case ACTIVE -> throw new CustomException(UserErrorCode.INVALID_REQUEST_PARAMETER);
        };

        List<UserJourney> userJourneyList = new ArrayList<>();

        //유저가 선택한 초기 여정 생성
        userJourneyList.add(
                UserJourney.builder()
                        .journeyStart(LocalDate.now())
                        .journey(initialJourney)
                        .journeyStatus(EJourneyStatus.BEFORE_START)
                        .user(user)
                        .build()
        );

        //레거시 여정 제외 여정 생성
        List<EJourney> lastJourneys = Arrays.stream(EJourney.values())
                .filter(journey -> journey != initialJourney)
                .filter(journey -> journey != EJourney.PROCESS_EMOTION) // 레거시 여정 차단
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
        this.journeyStart = LocalDate.now();
        this.journeyStatus = EJourneyStatus.IN_PROGRESS;
    }

    public void updateUserJourneyCompleted(){
        this.journeyStatus = EJourneyStatus.COMPLETED;
        this.journeyEnd = LocalDate.now();
    }

    public void updateNewUserJourney() {
        this.journeyStart = LocalDate.now();
        this.journeyStatus = EJourneyStatus.BEFORE_START;
    }
}
