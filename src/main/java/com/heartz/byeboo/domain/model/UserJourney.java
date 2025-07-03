package com.heartz.byeboo.domain.model;

import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserJourney {
    private Long id;
    private LocalDate journeyStart;
    private LocalDate journeyEnd;
    private EJourney journey;
    private EJourneyStatus journeyStatus;
    private User user;
}
