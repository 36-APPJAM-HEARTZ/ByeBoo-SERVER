package com.heartz.byeboo.domain.entity;

import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_journeys")
public class UserJourneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journey_start")
    private LocalDate journeyStart;

    @Column(name = "journey_end")
    private LocalDate journeyEnd;

    @Enumerated(EnumType.STRING)
    @Column(name = "journey", nullable = false, length = 50)
    private EJourney journey;

    @Enumerated(EnumType.STRING)
    @Column(name = "journey_status", nullable = false, length = 50)
    private EJourneyStatus journeyStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    public UserJourney toModel() {
        return UserJourney.builder()
                .journeyStart(journeyStart)
                .journeyEnd(journeyEnd)
                .journey(journey)
                .journeyStatus(journeyStatus)
                .user(user.toModel())
                .build();
    }
}
