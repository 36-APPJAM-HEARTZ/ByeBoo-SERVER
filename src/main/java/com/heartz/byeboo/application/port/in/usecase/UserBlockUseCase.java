package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.userblock.UserBlockCommand;

public interface UserBlockUseCase {
    Void block(UserBlockCommand command);
}
