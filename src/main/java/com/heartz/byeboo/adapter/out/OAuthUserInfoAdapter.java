package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.application.command.auth.ProviderUserInfoCommand;
import com.heartz.byeboo.application.command.auth.UserInfoCommand;
import com.heartz.byeboo.application.port.out.userinfo.RetrieveUserInfoPort;
import com.heartz.byeboo.application.port.out.userinfo.UnlinkUserInfoPort;
import com.heartz.byeboo.domain.type.EPlatform;
import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;
import com.heartz.byeboo.application.service.auth.OAuthProvider;
import com.heartz.byeboo.application.service.auth.OAuthProviderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuthUserInfoAdapter implements RetrieveUserInfoPort, UnlinkUserInfoPort {

    private final OAuthProviderFactory oAuthProviderFactory;

    @Override
    public SocialInfoResponse getUserInfo(UserInfoCommand command) {
        OAuthProvider oAuthProvider = oAuthProviderFactory.findProvider(command.platform());
        return oAuthProvider.getUserInfo(ProviderUserInfoCommand.of(command.token(), command.code()));
    }

    @Override
    public void revoke(EPlatform platform, String refreshToken, String serialId) {
        OAuthProvider oAuthProvider = oAuthProviderFactory.findProvider(platform);
        oAuthProvider.requestRevoke(refreshToken, serialId);
    }
}
