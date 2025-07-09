package com.heartz.byeboo.application.port.out.user;

import com.heartz.byeboo.domain.model.UserJourney;

public interface UpdateUserJourneyPort {
    void updateUserJourney(UserJourney userJourney);
    void updateUserJourneyCompleted(UserJourney userJourney);
}
