package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EPlatform;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.domain.type.ERole;
import com.heartz.byeboo.domain.type.EUserStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.heartz.byeboo.constants.AuthConstants.DELETED_USER_DEFAULT_INFO;
import static com.heartz.byeboo.domain.type.EUserStatus.ACTIVE;
import static com.heartz.byeboo.domain.type.EUserStatus.INACTIVE;

@Getter
@Builder
public class User {
    private Long id;
    private String name;
    private EQuestStyle questStyle;
    private Long currentNumber;
    private String serialId;
    private EPlatform platform;
    private ERole role;
    private EUserStatus status;
    private LocalDateTime deletedAt;

    public static User of(Long id, String name, EQuestStyle questStyle, Long currentNumber, EPlatform platform, ERole role, String serialId, EUserStatus status, LocalDateTime deletedAt) {
        return User.builder()
                .id(id)
                .name(name)
                .questStyle(questStyle)
                .currentNumber(currentNumber)
                .role(role)
                .serialId(serialId)
                .platform(platform)
                .status(status)
                .deletedAt(deletedAt)
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
    }

    public void updateStatus(final EUserStatus userStatus) {
        this.status = userStatus;
    }

    public void updateDeletedAt(final LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
