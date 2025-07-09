package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.QUserJourneyEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserJourneyEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserJourneyRepository;
import com.heartz.byeboo.application.port.out.CreateUserJourneyPort;
import com.heartz.byeboo.application.port.out.RetrieveUserJourneyPort;
import com.heartz.byeboo.application.port.out.UpdateUserJourneyPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserJourneyErrorCode;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.type.EJourneyStatus;
import com.heartz.byeboo.mapper.UserJourneyMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserJourneyPersistenceAdapter implements CreateUserJourneyPort, RetrieveUserJourneyPort, UpdateUserJourneyPort {
    private final UserJourneyRepository userJourneyRepository;
    private final JPAQueryFactory queryFactory;

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
    public void updateUserJourney(UserJourney userJourney) {
        QUserJourneyEntity userJourneyEntity = QUserJourneyEntity.userJourneyEntity;

        queryFactory
                .update(userJourneyEntity)
                .set(userJourneyEntity.journeyStatus, userJourney.getJourneyStatus())
                .set(userJourneyEntity.modifiedDate, LocalDateTime.now())
                .where(userJourneyEntity.id.eq(userJourney.getId()))
                .execute();
    }

    @Override
    public void updateUserJourneyCompleted(UserJourney userJourney) {
        QUserJourneyEntity userJourneyEntity = QUserJourneyEntity.userJourneyEntity;

        queryFactory
                .update(userJourneyEntity)
                .set(userJourneyEntity.journeyStatus, userJourney.getJourneyStatus())
                .set(userJourneyEntity.modifiedDate, LocalDateTime.now())
                .set(userJourneyEntity.journeyEnd, LocalDate.now())
                .where(userJourneyEntity.id.eq(userJourney.getId()))
                .execute();
    }
}
