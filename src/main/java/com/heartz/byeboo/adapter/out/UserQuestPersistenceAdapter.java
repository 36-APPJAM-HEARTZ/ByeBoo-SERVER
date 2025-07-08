package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserQuestRepository;
import com.heartz.byeboo.application.port.out.CreateUserQuestPort;
import com.heartz.byeboo.application.port.out.RetrieveUserQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.domain.model.UserQuest;
import com.heartz.byeboo.mapper.UserQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserQuestPersistenceAdapter implements CreateUserQuestPort, RetrieveUserQuestPort {

    private final UserQuestRepository userQuestRepository;

    @Override
    public void createUserQuest(UserQuest userQuest) {
        UserQuestEntity userQuestEntity = UserQuestMapper.toEntity(userQuest);
        userQuestRepository.save(userQuestEntity);
    }


    @Override
    public UserQuest getUserQuestByUserId(Long userId) {
        UserQuestEntity userQuestEntity = userQuestRepository.getByUserId(userId)
                .orElseThrow(() -> new CustomException(UserQuestErrorCode.USER_QUEST_NOT_FOUND));

        return

    }
}
