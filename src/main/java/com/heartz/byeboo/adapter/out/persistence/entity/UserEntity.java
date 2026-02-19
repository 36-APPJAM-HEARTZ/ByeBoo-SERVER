package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "quest_style", length = 50)
    private EQuestStyle questStyle;

    @Enumerated(EnumType.STRING)
    @Column(name = "journey", length = 50)
    private EJourney journey;

    @Column(name = "current_number")
    private Long currentNumber;

    @Column(name = "serial_id", nullable = false, unique = true, updatable = false)
    private String serialId;

    @Column(name = "platform", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EPlatform platform;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EUserStatus status;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "alarm_enabled", nullable = false)
    private boolean alarmEnabled = false;

    @Column(name = "profile_icon")
    @Enumerated(EnumType.STRING)
    private EProfileIcon profileIcon;

    @Builder
    public UserEntity(Long id, String name, EQuestStyle questStyle, EJourney journey,Long currentNumber,EPlatform platform, ERole role, String serialId, EUserStatus status, LocalDateTime deletedAt, String refreshToken, boolean alarmEnabled, EProfileIcon profileIcon) {
        this.id = id;
        this.name = name;
        this.questStyle = questStyle;
        this.journey = journey;
        this.currentNumber = currentNumber;
        this.platform = platform;
        this.role = role;
        this.serialId = serialId;
        this.status = status;
        this.deletedAt = deletedAt;
        this.refreshToken = refreshToken;
        this.alarmEnabled = alarmEnabled;
        this.profileIcon = profileIcon;
    }

    public static UserEntity create(String name, EQuestStyle questStyle, EJourney journey, Long currentNumber, EPlatform platform, ERole role, String serialId, EUserStatus status, LocalDateTime deletedAt, String refreshToken, boolean alarmEnabled, EProfileIcon profileIcon) {
        return UserEntity.builder()
                .name(name)
                .questStyle(questStyle)
                .journey(journey)
                .currentNumber(currentNumber)
                .platform(platform)
                .serialId(serialId)
                .role(role)
                .status(status)
                .deletedAt(deletedAt)
                .refreshToken(refreshToken)
                .alarmEnabled(alarmEnabled)
                .profileIcon(profileIcon)
                .build();
    }

    public static UserEntity createForUpdate(Long id, String name, EQuestStyle questStyle, EJourney journey,Long currentNumber, EPlatform platform, ERole role, String serialId, EUserStatus status, LocalDateTime deletedAt, String refreshToken, boolean alarmEnabled, EProfileIcon profileIcon) {
        return UserEntity.builder()
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
                .profileIcon(profileIcon)
                .build();
    }
}
