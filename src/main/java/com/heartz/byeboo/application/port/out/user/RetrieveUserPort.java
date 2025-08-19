package com.heartz.byeboo.application.port.out.user;

import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.type.EPlatform;

import java.util.Optional;

public interface RetrieveUserPort {
    User getUserById(Long userId);
    Optional<User> findUserByPlatFormAndSeralId(EPlatform platform, String serialId);
}
