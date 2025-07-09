package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.type.EJourney;

import java.util.List;

public interface RetrieveUserJourneyPort {
    UserJourney getOngoingUserJourneyByUser(User user);
    List<UserJourney> getJourneysByUser(User user);
    UserJourney getUserJourneyByUserAndJourney(User user, EJourney journey);
}
