package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserCommonQuestRepository;
import com.heartz.byeboo.application.port.out.usercommonquest.CreateUserCommonQuestPort;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.mapper.UserCommonQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserCommonQuestPersistenceAdapter implements CreateUserCommonQuestPort {

    private final UserCommonQuestRepository userCommonQuestRepository;

    @Override
    public void createUserCommonQuest(UserCommonQuest userCommonQuest) {
        UserCommonQuestEntity userCommonQuestEntity = UserCommonQuestMapper.toEntity(userCommonQuest);
        userCommonQuestRepository.save(userCommonQuestEntity);
    }
}
