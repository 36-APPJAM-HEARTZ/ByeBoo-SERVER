package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.heartz.byeboo.constants.AuthConstants.DELETED_USER_DEFAULT_INFO;
import static com.heartz.byeboo.domain.type.EUserStatus.INACTIVE;

@Getter
@Builder
public class User {
    private Long id;
    private String name;
    private EQuestStyle questStyle;
    private EJourney journey;
    private Long currentNumber;
    private String serialId;
    private EPlatform platform;
    private ERole role;
    private EUserStatus status;
    private LocalDateTime deletedAt;
    private String refreshToken;
    private boolean alarmEnabled;

    public static User of(Long id, String name, EQuestStyle questStyle, EJourney journey, Long currentNumber, EPlatform platform, ERole role, String serialId, EUserStatus status, LocalDateTime deletedAt, String refreshToken, boolean alarmEnabled) {
        return User.builder()
                .id(id)
                .name(name)
                .questStyle(questStyle)
                .journey(journey)
                .currentNumber(currentNumber)
                .role(role)
                .serialId(serialId)
                .platform(platform)
                .status(status)
                .deletedAt(deletedAt)
                .refreshToken(refreshToken)
                .alarmEnabled(alarmEnabled)
                .build();
    }

    public static User ofAdmin(){
        return User.builder()
                .serialId("ADMIN_TEST")
                .platform(EPlatform.KAKAO)
                .role(ERole.ADMIN)
                .refreshToken(null)
                .build();
    }


    public void initializeCurrentNumber() {
        this.currentNumber = 0L;
    }

    public void startNewJourney(){
        this.currentNumber = 1L;
    }

    public void updateQuestStyle(EQuestStyle questStyle){
        this.questStyle = questStyle;
    }

    public void updateCurrentNumber(){
        this.currentNumber++;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void softDelete() {
        updateStatus(INACTIVE);
        updateDeletedAt(LocalDateTime.now());
        this.name = DELETED_USER_DEFAULT_INFO;
        deleteRefreshToken();
    }

    public void updateStatus(final EUserStatus userStatus) {
        this.status = userStatus;
    }

    public void updateDeletedAt(final LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void updateJourney(final EJourney journey) {
        this.journey = journey;
    }

    public void deleteRefreshToken(){
        this.refreshToken = null;
    }

    public void updateAlarmState(boolean alarmState){
        this.alarmEnabled = alarmState;
    }
}
