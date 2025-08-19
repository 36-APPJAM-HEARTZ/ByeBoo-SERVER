package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EPlatform;
import com.heartz.byeboo.domain.type.EQuestStyle;
import com.heartz.byeboo.domain.type.ERole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public UserEntity(Long id, String name, EQuestStyle questStyle, Long currentNumber,EPlatform platform, ERole role, String serialId) {
        this.id = id;
        this.name = name;
        this.questStyle = questStyle;
        this.currentNumber = currentNumber;
        this.platform = platform;
        this.role = role;
        this.serialId = serialId;
    }

    public static UserEntity create(String name, EQuestStyle questStyle, Long currentNumber, EPlatform platform, ERole role, String serialId) {
        return UserEntity.builder()
                .name(name)
                .questStyle(questStyle)
                .currentNumber(currentNumber)
                .platform(platform)
                .serialId(serialId)
                .role(role)
                .build();
    }

    public static UserEntity createForUpdate(Long id, String name, EQuestStyle questStyle, Long currentNumber) {
        return UserEntity.builder()
                .id(id)
                .name(name)
                .questStyle(questStyle)
                .currentNumber(currentNumber)
                .build();
    }
}
