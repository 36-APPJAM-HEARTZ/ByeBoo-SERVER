package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.application.command.auth.OAuthLoginCommand;
import com.heartz.byeboo.application.command.auth.OAuthLogoutCommand;
import com.heartz.byeboo.application.command.auth.OAuthWithdrawCommand;

public interface OAuthUseCase {
    UserLoginResponse login(OAuthLoginCommand command);
    Void logout(OAuthLogoutCommand command);
    Void withdraw(OAuthWithdrawCommand command);
}
