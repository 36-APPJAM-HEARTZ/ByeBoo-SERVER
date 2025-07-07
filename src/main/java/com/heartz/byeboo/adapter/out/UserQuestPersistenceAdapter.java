package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserQuestRepository;
import com.heartz.byeboo.application.port.out.CreateUserQuestPort;
import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.mapper.UserQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserQuestPersistenceAdapter implements CreateUserQuestPort {

    private final UserQuestRepository userQuestRepository;

    @Override
    public void createUserQuest(UserQuest userQuest) {
        UserQuestEntity userQuestEntity = UserQuestMapper.toEntity(userQuest);
        userQuestRepository.save(userQuestEntity);
    }
}
