package com.heartz.byeboo.security.usecase;

import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.security.command.OAuthLoginCommand;
import com.heartz.byeboo.security.command.OAuthLogoutCommand;
import com.heartz.byeboo.security.command.OAuthWithdrawCommand;

public interface OAuthUseCase {
    UserLoginResponse login(OAuthLoginCommand command);
    Void logout(OAuthLogoutCommand command);
    Void withdraw(OAuthWithdrawCommand command);
}
