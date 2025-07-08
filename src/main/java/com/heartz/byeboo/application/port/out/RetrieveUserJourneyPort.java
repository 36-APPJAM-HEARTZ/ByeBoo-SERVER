package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;

public interface RetrieveUserJourneyPort {
    UserJourney getOngoingUserJourneyByUser(User user);
}
