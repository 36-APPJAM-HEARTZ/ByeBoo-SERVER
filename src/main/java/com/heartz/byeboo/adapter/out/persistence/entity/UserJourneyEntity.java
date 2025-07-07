package com.heartz.byeboo.adapter.out.persistence.entity;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_journeys")
public class UserJourneyEntity extends BaseEntity{
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

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Builder
    public UserJourneyEntity(
            LocalDate journeyStart,
            LocalDate journeyEnd,
            EJourney journey,
            EJourneyStatus journeyStatus,
            Long userId
    ) {
        this.journeyStart = journeyStart;
        this.journeyEnd = journeyEnd;
        this.journey = journey;
        this.journeyStatus = journeyStatus;
        this.userId = userId;
    }

    public static UserJourneyEntity create(
            LocalDate journeyStart,
            LocalDate journeyEnd,
            EJourney journey,
            EJourneyStatus journeyStatus,
            Long userId
    ) {
        return UserJourneyEntity.builder()
                .journeyStart(journeyStart)
                .journeyEnd(journeyEnd)
                .journey(journey)
                .journeyStatus(journeyStatus)
                .userId(userId)
                .build();
    }
}
