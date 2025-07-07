package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.QUserEntity;
import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserRepository;
import com.heartz.byeboo.application.port.out.CreateUserPort;
import com.heartz.byeboo.application.port.out.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.UpdateUserPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserErrorCode;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.mapper.UserMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public void updateCurrentNumber(Long userId) {
        QUserEntity user = QUserEntity.userEntity;

        queryFactory
                .update(user)
                .set(user.currentNumber, user.currentNumber.add(1))
                .where(user.id.eq(userId))
                .execute();
    }
}
