package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserRepository;
import com.heartz.byeboo.application.port.out.CreateUserPort;
import com.heartz.byeboo.application.port.out.RetrieveUserPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserErrorCode;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements CreateUserPort, RetrieveUserPort {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        UserEntity saveEntity = userRepository.save(userEntity);

        return UserMapper.toDomain(saveEntity);
    }


    @Override
    public User findById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        return UserMapper.toDomain(userEntity);
    }
}
