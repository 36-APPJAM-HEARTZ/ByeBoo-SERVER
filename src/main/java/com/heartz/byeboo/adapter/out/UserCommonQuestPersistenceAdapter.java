package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.UserCommonQuestRepository;
import com.heartz.byeboo.application.port.out.usercommonquest.CreateUserCommonQuestPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.application.port.out.usercommonquest.UpdateUserCommonQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserCommonQuestErrorCode;
import com.heartz.byeboo.domain.model.CommonQuest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.mapper.UserCommonQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserCommonQuestPersistenceAdapter implements CreateUserCommonQuestPort, RetrieveUserCommonQuestPort, UpdateUserCommonQuestPort {

    private final UserCommonQuestRepository userCommonQuestRepository;

    @Override
    public void createUserCommonQuest(UserCommonQuest userCommonQuest) {
        UserCommonQuestEntity userCommonQuestEntity = UserCommonQuestMapper.toEntity(userCommonQuest);
        userCommonQuestRepository.save(userCommonQuestEntity);
    }

    @Override
    public boolean isUserCommonQuestExistsToday(User user) {
        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
        LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);

        return userCommonQuestRepository.existsByUserIdAndCreatedDateBetween(user.getId(), startOfToday, endOfToday);
    }

    @Override
    public void deleteByUserIdAndId(Long userCommonQuestId, User user) {
        long deletedCount = userCommonQuestRepository.deleteByUserIdAndId(user.getId(), userCommonQuestId);

        if (deletedCount == 0) {
            throw new CustomException(UserCommonQuestErrorCode.USER_COMMON_QUEST_NOT_FOUND);
        }
    }

    @Override
    public UserCommonQuest getUserCommonQuestByUserAndId(User user, Long id) {
        UserCommonQuestEntity commonQuestEntity = userCommonQuestRepository.findByUserIdAndId(user.getId(), id)
                .orElseThrow(() -> new CustomException(UserCommonQuestErrorCode.USER_COMMON_QUEST_NOT_FOUND));

        // 엔티티 -> 도메인 매핑 문제! => 가짜 객체 주입
        CommonQuest referenceQuest = CommonQuest.fromId(commonQuestEntity.getCommonQuestId());
        return UserCommonQuestMapper.toDomain(commonQuestEntity, user, referenceQuest);

    }

    @Override
    public List<UserCommonQuest> getUserCommonQuestsByCreatedDate(LocalDate targetDate, Long cursor, int limit, CommonQuest commonQuest) {
        LocalDateTime startOfToday = targetDate.atStartOfDay();
        LocalDateTime endOfToday = targetDate.atTime(LocalTime.MAX);

        List<UserCommonQuestEntity> commonQuestEntityList = userCommonQuestRepository.findByDateAndCursor(
                startOfToday, endOfToday, cursor, Limit.of(limit)
        );

        return commonQuestEntityList.stream().map(userCommonQuestEntity ->
                UserCommonQuestMapper.toDomain(userCommonQuestEntity, User.fromId(userCommonQuestEntity.getUserId()), commonQuest))
                .toList();
    }

    @Override
    public long countByCreatedDateBetween(LocalDate targetDate) {
        LocalDateTime startOfToday = targetDate.atStartOfDay();
        LocalDateTime endOfToday = targetDate.atTime(LocalTime.MAX);

        return userCommonQuestRepository.countByCreatedDateBetween(startOfToday, endOfToday);
    }

    @Override
    public UserCommonQuest getUserCommonQuestById(Long id) {
        UserCommonQuestEntity userCommonQuestEntity = userCommonQuestRepository.findById(id)
                .orElseThrow(() -> new CustomException(UserCommonQuestErrorCode.USER_COMMON_QUEST_NOT_FOUND));

        CommonQuest referenceQuest = CommonQuest.fromId(userCommonQuestEntity.getCommonQuestId());
        User referenceUser = User.fromId(userCommonQuestEntity.getUserId());
        return UserCommonQuestMapper.toDomain(userCommonQuestEntity, referenceUser, referenceQuest);
    }

    @Override
    public List<UserCommonQuest> getUserCommonQuestsByUserId(User user) {
        List<UserCommonQuestEntity> userCommonQuestEntityList = userCommonQuestRepository.findAllByUserId(user.getId());

        return userCommonQuestEntityList.stream().map(userCommonQuestEntity ->
                UserCommonQuestMapper.toDomain(userCommonQuestEntity, user, CommonQuest.fromId(userCommonQuestEntity.getCommonQuestId())))
                .toList();
    }

    @Override
    public void updateUserCommonQuest(UserCommonQuest userCommonQuest) {
        UserCommonQuestEntity userCommonQuestEntity = UserCommonQuestMapper.toEntityForUpdate(userCommonQuest);

        userCommonQuestRepository.save(userCommonQuestEntity);
    }
}
