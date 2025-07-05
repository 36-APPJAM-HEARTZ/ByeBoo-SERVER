package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.UserJourney;

import java.util.List;

public interface CreateUserJourneyPort {
    void createUserJourney(List<UserJourney> userJourneyList);
}
