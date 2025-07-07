package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.User;

public interface RetrieveUserPort {
    User getUserById(Long userId);
}
