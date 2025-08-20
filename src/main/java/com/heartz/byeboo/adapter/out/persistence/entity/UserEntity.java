package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EPlatform;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.domain.type.ERole;
import com.heartz.byeboo.domain.type.EUserStatus;
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

    @Builder
    public UserEntity(Long id, String name, EQuestStyle questStyle, Long currentNumber,EPlatform platform, ERole role, String serialId, EUserStatus status, LocalDateTime deletedAt) {
        this.id = id;
        this.name = name;
        this.questStyle = questStyle;
        this.currentNumber = currentNumber;
        this.platform = platform;
        this.role = role;
        this.serialId = serialId;
        this.status = status;
        this.deletedAt = deletedAt;
    }

    public static UserEntity create(String name, EQuestStyle questStyle, Long currentNumber, EPlatform platform, ERole role, String serialId, EUserStatus status, LocalDateTime deletedAt) {
        return UserEntity.builder()
                .name(name)
                .questStyle(questStyle)
                .currentNumber(currentNumber)
                .platform(platform)
                .serialId(serialId)
                .role(role)
                .status(status)
                .deletedAt(deletedAt)
                .build();
    }

    public static UserEntity createForUpdate(Long id, String name, EQuestStyle questStyle, Long currentNumber, EPlatform platform, ERole role, String serialId, EUserStatus status, LocalDateTime deletedAt) {
        return UserEntity.builder()
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
}
