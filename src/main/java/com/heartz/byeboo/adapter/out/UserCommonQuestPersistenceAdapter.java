package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserCommonQuestRepository;
import com.heartz.byeboo.application.port.out.usercommonquest.CreateUserCommonQuestPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.mapper.UserCommonQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class UserCommonQuestPersistenceAdapter implements CreateUserCommonQuestPort, RetrieveUserCommonQuestPort {

    private final UserCommonQuestRepository userCommonQuestRepository;

    @Override
    public void createUserCommonQuest(UserCommonQuest userCommonQuest) {
        UserCommonQuestEntity userCommonQuestEntity = UserCommonQuestMapper.toEntity(userCommonQuest);
        userCommonQuestRepository.save(userCommonQuestEntity);
    }

    @Override
    public boolean isUserCommonQuestExists(User user) {
        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
        LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);

        return userCommonQuestRepository.existsByUserIdAndCreatedDateBetween(user.getId(), startOfToday, endOfToday);
    }
}
