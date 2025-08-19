package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.application.port.out.RetrieveUserInfoPort;
import com.heartz.byeboo.domain.type.EPlatform;
import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;
import com.heartz.byeboo.security.service.OAuthProvider;
import com.heartz.byeboo.security.service.OAuthProviderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuthUserInfoAdapter implements RetrieveUserInfoPort {

    private final OAuthProviderFactory oAuthProviderFactory;

    @Override
    public SocialInfoResponse getUserInfo(String token, EPlatform platform) {
        OAuthProvider oAuthProvider = oAuthProviderFactory.findProvider(platform);
        return oAuthProvider.getUserInfo(token);
    }
}
