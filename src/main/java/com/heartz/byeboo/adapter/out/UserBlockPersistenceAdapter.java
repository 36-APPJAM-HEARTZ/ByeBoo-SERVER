package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserBlockEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserBlockRepository;
import com.heartz.byeboo.application.port.out.userblock.CreateUserBlockPort;
import com.heartz.byeboo.domain.model.UserBlock;
import com.heartz.byeboo.mapper.UserBlockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBlockPersistenceAdapter implements CreateUserBlockPort {

    private final UserBlockRepository userBlockRepository;

    @Override
    public void createUserBlock(UserBlock userBlock) {
        UserBlockEntity userBlockEntity = UserBlockMapper.toEntity(userBlock);
        userBlockRepository.save(userBlockEntity);
    }
}
