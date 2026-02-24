package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.userblock.UserBlockCommand;
import com.heartz.byeboo.application.port.in.dto.response.userblock.UserBlockListResponseDto;

import java.util.List;

public interface UserBlockUseCase {
    Void block(UserBlockCommand command);
    UserBlockListResponseDto getUserBlockList(Long userId);
}
