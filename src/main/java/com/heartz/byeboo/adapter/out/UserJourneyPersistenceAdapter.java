package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserJourneyEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserJourneyRepository;
import com.heartz.byeboo.application.port.out.user.CreateUserJourneyPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserJourneyPort;
import com.heartz.byeboo.application.port.out.user.UpdateUserJourneyPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserJourneyErrorCode;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.type.EJourney;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import com.heartz.byeboo.mapper.UserJourneyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserJourneyPersistenceAdapter implements CreateUserJourneyPort, RetrieveUserJourneyPort, UpdateUserJourneyPort {
    private final UserJourneyRepository userJourneyRepository;

    @Override
    public void createUserJourney(List<UserJourney> userJourneyList) {
        List<UserJourneyEntity> userJourneyEntityList = userJourneyList.stream()
                .map(UserJourneyMapper::toEntity)
                .toList();

        userJourneyRepository.saveAll(userJourneyEntityList);
    }

    @Override
    public UserJourney getOngoingUserJourneyByUser(User user) {
        return userJourneyRepository.findAllByUserId(user.getId()).stream()
                .map(userJourneyEntity ->  UserJourneyMapper.toDomain(userJourneyEntity, user))
                .filter(userJourney -> userJourney.getJourneyStatus().equals(EJourneyStatus.BEFORE_START) ||
                        userJourney.getJourneyStatus().equals(EJourneyStatus.IN_PROGRESS))
                .findFirst()
                .orElseThrow(() -> new CustomException(UserJourneyErrorCode.NOT_FOUND_ONGOING_USER_JOURNEY));
    }

    @Override
    public List<UserJourney> getJourneysByUser(User user) {
        return userJourneyRepository.findAllByUserId(user.getId()).stream().map(
                userJourneyEntity -> UserJourneyMapper.toDomain(userJourneyEntity, user)
        ).toList();
    }

    @Override
    public UserJourney getUserJourneyByUserAndJourney(User user, EJourney journey) {
        UserJourneyEntity userJourneyEntity = userJourneyRepository.findByUserIdAndJourney(user.getId(), journey)
                .orElseThrow(() -> new CustomException(UserJourneyErrorCode.NOT_FOUND_USER_JOURNEY));

        return UserJourneyMapper.toDomain(userJourneyEntity, user);
    }

    @Override
    public void updateUserJourney(UserJourney userJourney) {
        UserJourneyEntity userJourneyEntity = UserJourneyMapper.toEntityForUpdate(userJourney);

        userJourneyRepository.save(userJourneyEntity);
    }

    @Override
    public void updateUserJourneyCompleted(UserJourney userJourney) {
        UserJourneyEntity userJourneyEntity = UserJourneyMapper.toEntityForUpdate(userJourney);

        userJourneyRepository.save(userJourneyEntity);
    }

}
