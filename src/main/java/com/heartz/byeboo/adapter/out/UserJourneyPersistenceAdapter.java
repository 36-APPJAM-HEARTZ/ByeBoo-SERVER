package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserJourneyEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserJourneyRepository;
import com.heartz.byeboo.application.port.out.CreateUserJourneyPort;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.mapper.UserJourneyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserJourneyPersistenceAdapter implements CreateUserJourneyPort {
    private final UserJourneyRepository userJourneyRepository;

    @Override
    public void createUserJourney(List<UserJourney> userJourneyList) {
        List<UserJourneyEntity> userJourneyEntityList = userJourneyList.stream()
                .map(UserJourneyMapper::toEntity)
                .toList();

        userJourneyRepository.saveAll(userJourneyEntityList);
    }
}
