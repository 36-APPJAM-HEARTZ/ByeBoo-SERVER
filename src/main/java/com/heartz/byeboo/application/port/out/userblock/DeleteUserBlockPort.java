package com.heartz.byeboo.application.port.out.userblock;

public interface DeleteUserBlockPort {
    void deleteUserBlockById(Long blockId);
    void deleteAllUserBlockByUserId(Long userId);
}
