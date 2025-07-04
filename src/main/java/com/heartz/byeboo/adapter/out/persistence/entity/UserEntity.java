package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EQuestStyle;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "quest_style", nullable = false, length = 50)
    private EQuestStyle questStyle;

    @Column(name = "current_number")
    private Long currentNumber;

    @Builder
    public UserEntity(String name, EQuestStyle questStyle, Long currentNumber) {
        this.name = name;
        this.questStyle = questStyle;
        this.currentNumber = currentNumber;
    }

    public static UserEntity create(String name, EQuestStyle questStyle, Long currentNumber) {
        return UserEntity.builder()
                .name(name)
                .questStyle(questStyle)
                .currentNumber(currentNumber)
                .build();
    }
}
