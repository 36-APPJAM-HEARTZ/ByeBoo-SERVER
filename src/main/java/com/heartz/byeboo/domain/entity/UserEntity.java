package com.heartz.byeboo.domain.entity;

import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.type.EQuestStyle;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    public User toModel() {
        return User.builder()
                .name(name)
                .questStyle(questStyle)
                .currentNumber(currentNumber)
                .build();
    }
}
