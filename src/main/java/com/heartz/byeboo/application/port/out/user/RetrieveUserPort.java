package com.heartz.byeboo.application.port.out.user;

import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserIdCurrentNumberProjection;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.type.EPlatform;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RetrieveUserPort {
    User getUserById(Long userId);
    Optional<User> findUserByPlatFormAndSeralId(EPlatform platform, String serialId);
    Long countAllUsers();
    List<UserIdCurrentNumberProjection> findUsersWithExpiredQuest(LocalDateTime thresholdStart, LocalDateTime thresholdEnd);
}
