package com.heartz.byeboo.application.port.out;

import com.heartz.byeboo.domain.model.User;

public interface CreateUserPort {
    User createUser(User user);
}
