package com.heartz.byeboo.application.port.out.userinfo;

import com.heartz.byeboo.application.command.auth.UserInfoCommand;
import com.heartz.byeboo.domain.type.EPlatform;
import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;

public interface RetrieveUserInfoPort {
    SocialInfoResponse getUserInfo(UserInfoCommand command);
}
