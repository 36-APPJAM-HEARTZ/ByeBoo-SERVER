package com.heartz.byeboo.mapper;

import com.heartz.byeboo.adapter.out.persistence.entity.UserJourneyEntity;
import com.heartz.byeboo.domain.model.User;
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

    public static UserJourney toDomain(UserJourneyEntity userJourneyEntity, User user) {
        return UserJourney.of(
                userJourneyEntity.getId(),
                userJourneyEntity.getJourneyStart(),
                userJourneyEntity.getJourneyEnd(),
                userJourneyEntity.getJourney(),
                userJourneyEntity.getJourneyStatus(),
                user
        );
    }

    public static UserJourneyEntity toEntityForUpdate(UserJourney userJourney) {
        return UserJourneyEntity.createForUpdate(
                userJourney.getId(),
                userJourney.getJourneyStart(),
                userJourney.getJourneyEnd(),
                userJourney.getJourney(),
                userJourney.getJourneyStatus(),
                userJourney.getUser().getId()
        );
    }
}
