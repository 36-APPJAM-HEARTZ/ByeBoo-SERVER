package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserRepository;
import com.heartz.byeboo.application.port.out.user.CreateUserPort;
import com.heartz.byeboo.application.port.out.user.DeleteUserPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.user.UpdateUserPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserErrorCode;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.type.EPlatform;
import com.heartz.byeboo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements CreateUserPort, RetrieveUserPort, UpdateUserPort, DeleteUserPort {
    private final UserRepository userRepository;


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
    public Optional<User> findUserByPlatFormAndSeralId(EPlatform platform, String serialId) {
        return Optional.ofNullable(userRepository.findByPlatformAndSerialId(platform, serialId))
                .map(UserMapper::toDomain);
    }


    @Override
    public void updateCurrentNumber(User user) {
        UserEntity userEntity = UserMapper.toEntityForUpdate(user);

        userRepository.save(userEntity);
    }

    @Override
    public void updateName(User user) {
        UserEntity userEntity = UserMapper.toEntityForUpdate(user);

        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(User user) {
        UserEntity userEntity = UserMapper.toEntityForUpdate(user);
        userRepository.save(userEntity);
    }

    @Override
    public void updateAlarmEnabled(User user) {
        UserEntity userEntity = UserMapper.toEntityForUpdate(user);
        userRepository.save(userEntity);
    }


    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Long countAllUsers() { return userRepository.count();}

    @Override
    public boolean isAlarmEnabledById(Long userId) {
        return userRepository.isAlarmEnabledById(userId);
    }
}
