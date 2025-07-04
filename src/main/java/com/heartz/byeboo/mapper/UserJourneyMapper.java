package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserJourneyEntity;
import com.heartz.byeboo.domain.model.UserJourney;

public class UserJourneyMapper {
    public static UserJourneyEntity toEntity(UserJourney userJourney) {
        return UserJourneyEntity.create(
                userJourney.getJourneyStart(),
                userJourney.getJourneyEnd(),
                userJourney.getJourney(),
                userJourney.getJourneyStatus(),
                userJourney.getUser().getId()
        );
    }
}
