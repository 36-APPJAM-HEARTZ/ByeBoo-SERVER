package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserQuestRepository;
import com.heartz.byeboo.application.port.out.CreateUserQuestPort;
import com.heartz.byeboo.application.port.out.RetrieveUserQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.User;
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
    public UserQuest getUserQuestByUserAndQuest( User user, Quest quest) {
        UserQuestEntity userQuestEntity = userQuestRepository.findByUserIdAndQuestId(user.getId(), quest.getId())
                .orElseThrow(() -> new CustomException(UserQuestErrorCode.USER_QUEST_NOT_FOUND));

        return UserQuestMapper.toDomain(userQuestEntity, user, quest);
    }

    @Override
    public UserQuest getRecentUserQuestByUserAndQuest(User user, Quest quest) {
        UserQuestEntity userQuestEntity = userQuestRepository
                .findAllByUserIdAndQuestIdOrderByCreatedDateDesc(user.getId(), quest.getId())
                .stream()
                .findFirst()
                .orElse(null);

        if(userQuestEntity == null)
            return null;

        return UserQuestMapper.toDomain(userQuestEntity, user, quest);
    }
}
