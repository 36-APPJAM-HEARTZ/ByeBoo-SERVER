package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;

public interface RetrieveUserJourneyPort {
    UserJourney getUserJourneyByUser(User user);
}
