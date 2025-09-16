package com.heartz.byeboo.application.port.out.user;

import com.heartz.byeboo.domain.model.User;

public interface DeleteUserPort {
    void deleteUserById(Long userId);
}
