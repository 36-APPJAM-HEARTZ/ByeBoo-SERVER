package com.heartz.byeboo.infrastructure.api.apple;

import com.heartz.byeboo.application.command.auth.ProviderUserInfoCommand;
import com.heartz.byeboo.constants.AuthConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.AuthErrorCode;
import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;
import com.heartz.byeboo.infrastructure.dto.apple.ApplePublicKeys;
import com.heartz.byeboo.infrastructure.dto.apple.AppleTokenResponse;
import com.heartz.byeboo.application.service.auth.OAuthProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AppleOAuthProvider implements OAuthProvider {

    private final AppleFeignClient appleFeignClient;
    private final AppleIdentityTokenParser appleIdentityTokenParser;
    private final ApplePublicKeyGenerator applePublicKeyGenerator;;
    private final AppleClientSecretGenerator appleClientSecretGenerator;

    @Value("${oauth.apple.client-id}")
    private String clientId;

    @Override
    public SocialInfoResponse getUserInfo(final ProviderUserInfoCommand command) {
        Map<String, String> headers = appleIdentityTokenParser.parseHeaders(command.providerToken());
        ApplePublicKeys applePublicKeys = appleFeignClient.getApplePublicKey();

        PublicKey applePublicKey = applePublicKeyGenerator.generatePublicKey(headers, applePublicKeys);
        Claims claims = appleIdentityTokenParser.parsePublicKeyAndGetClaims(command.providerToken(), applePublicKey);
        String refreshToken;
        try {
            String clientSecret = appleClientSecretGenerator.generateClientSecret();
            refreshToken = getAppleRefreshToken(command.authorizationCode(), clientSecret);
        } catch (Exception e) {
            throw new CustomException(AuthErrorCode.APPLE_LOGIN_FAILED);
        }
        return SocialInfoResponse.of(
                claims.get("sub").toString(),
                refreshToken
        );
    }

    @Override
    public void requestRevoke(String refreshToken, final String serialId) {
        try {
            System.out.println("리프레"+refreshToken);
            String clientSecret = appleClientSecretGenerator.generateClientSecret();
            appleFeignClient.revoke(refreshToken, clientId, clientSecret, AuthConstants.OAuth2.TOKEN_TYPE_REFRESH);
        } catch (Exception e) {
            throw new CustomException(AuthErrorCode.APPLE_REVOKE_FAILED);
        }
    }

    private String getAppleRefreshToken(final String code, final String clientSecret) {
        try {
            AppleTokenResponse appleTokenResponse = appleFeignClient.getAppleTokens(code, clientId, clientSecret, AuthConstants.OAuth2.GRANT_TYPE_AUTHORIZATION_CODE);
            return appleTokenResponse.refreshToken();
        } catch (Exception e) {
            throw new CustomException(AuthErrorCode.FAILED_TO_LOAD_PRIVATE_KEY);
        }
    }
}
