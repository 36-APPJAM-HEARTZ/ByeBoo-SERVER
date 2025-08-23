package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.auth.OAuthLoginCommand;
import com.heartz.byeboo.application.command.auth.OAuthLogoutCommand;
import com.heartz.byeboo.application.command.auth.OAuthWithdrawCommand;
import com.heartz.byeboo.application.command.auth.ReissueCommand;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserReissueResponse;

public interface OAuthUseCase {
    UserLoginResponse login(OAuthLoginCommand command);
    Void logout(OAuthLogoutCommand command);
    Void withdraw(OAuthWithdrawCommand command);
    UserReissueResponse reissue(ReissueCommand reissueCommand);
}
