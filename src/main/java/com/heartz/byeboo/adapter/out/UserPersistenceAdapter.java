package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.QUserEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserRepository;
import com.heartz.byeboo.application.port.out.user.CreateUserPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.user.UpdateUserPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserErrorCode;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.mapper.UserMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements CreateUserPort, RetrieveUserPort, UpdateUserPort {
    private final UserRepository userRepository;
    private final JPAQueryFactory queryFactory;


    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        UserEntity saveEntity = userRepository.save(userEntity);

        return UserMapper.toDomain(saveEntity);
    }


    @Override
    public User getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        return UserMapper.toDomain(userEntity);
    }


    @Override
    public void updateCurrentNumber(User user) {
        QUserEntity userEntity = QUserEntity.userEntity;

        queryFactory
                .update(userEntity)
                .set(userEntity.currentNumber, user.getCurrentNumber())
                .set(userEntity.modifiedDate, LocalDateTime.now())
                .where(userEntity.id.eq(user.getId()))
                .execute();
    }
}
