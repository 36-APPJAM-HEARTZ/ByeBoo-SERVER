package com.heartz.byeboo.application.service.auth;

import com.heartz.byeboo.adapter.out.OAuthUserInfoAdapter;
import com.heartz.byeboo.application.command.auth.*;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserInfoResponse;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserReissueResponse;
import com.heartz.byeboo.application.port.in.usecase.OAuthUseCase;
import com.heartz.byeboo.application.port.out.notificationtoken.DeleteNotificationTokenPort;
import com.heartz.byeboo.application.port.out.token.CreateTokenPort;
import com.heartz.byeboo.application.port.out.token.DeleteTokenPort;
import com.heartz.byeboo.application.port.out.token.RetrieveTokenPort;
import com.heartz.byeboo.application.port.out.token.UpdateTokenPort;
import com.heartz.byeboo.application.port.out.user.*;
import com.heartz.byeboo.application.port.out.userquest.DeleteUserQuestPort;
import com.heartz.byeboo.domain.model.Token;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserJourney;
import com.heartz.byeboo.domain.type.ERole;
import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;
import com.heartz.byeboo.mapper.UserMapper;
import com.heartz.byeboo.security.jwt.JwtProvider;
import com.heartz.byeboo.security.jwt.JwtValidator;
import com.heartz.byeboo.security.jwt.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.heartz.byeboo.domain.type.EUserStatus.ACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService implements OAuthUseCase {

    private final OAuthUserInfoAdapter oAuthUserInfoAdapter;
    private final RetrieveUserPort retrieveUserPort;
    private final CreateUserPort createUserPort;
    private final JwtProvider jwtProvider;
    private final JwtValidator jwtValidator;
    private final CreateTokenPort createTokenPort;
    private final DeleteTokenPort deleteTokenPort;
    private final RetrieveTokenPort retrieveTokenPort;
    private final UpdateTokenPort updateTokenPort;
    private final RetrieveUserJourneyPort retrieveUserJourneyPort;
    private final DeleteUserPort deleteUserPort;
    private final DeleteUserQuestPort deleteUserQuestPort;
    private final DeleteUserJourneyPort deleteUserJourneyPort;
    private final DeleteNotificationTokenPort deleteNotificationTokenPort;


    @Transactional
    @Override
    public UserLoginResponse login(OAuthLoginCommand command) {
        SocialInfoResponse response = oAuthUserInfoAdapter.getUserInfo(UserInfoCommand.of(command.platform(), command.token(), command.code()));
        UserInfoResponse userInfoResponse = UserInfoResponse.of(command.platform(), response.serialId(), response.refreshToken());
        Optional<User> user = retrieveUserPort.findUserByPlatFormAndSeralId(userInfoResponse.platform(), userInfoResponse.serialId());
        boolean isRegistered = isRegistered(user);
        User findUser = loadOrCreateUser(user, userInfoResponse);
        TokenResponse issuedTokenResponse = jwtProvider.issueTokens(findUser.getId(), getUserRole(findUser.getId()));
        Token token = Token.of(findUser.getId(), issuedTokenResponse.refreshToken());
        createTokenPort.createToken(token);

        if(isRegistered){
            UserJourney userJourney = retrieveUserJourneyPort.getUserJourneyByUserAndJourney(findUser, findUser.getJourney());

            return UserLoginResponse.of(
                    issuedTokenResponse,
                    isRegistered,
                    findUser.getName(),
                    userJourney.getJourney(),
                    userJourney.getJourneyStatus(),
                    findUser.getId()
            );
        }

        return UserLoginResponse.of(
                issuedTokenResponse,
                isRegistered,
                null,
                null,
                null,
                findUser.getId()
        );
    }

    @Override
    public Void logout(OAuthLogoutCommand command) {
        User findUser = retrieveUserPort.getUserById(command.userId());
        deleteTokenPort.deleteToken(findUser.getId());

        return null;
    }

    @Override
    @Transactional
    public Void withdraw(OAuthWithdrawCommand command) {
        User findUser = retrieveUserPort.getUserById(command.userId());
        oAuthUserInfoAdapter.revoke(findUser.getPlatform(), findUser.getRefreshToken(), findUser.getSerialId());
        deleteUserQuestPort.deleteAllByUserId(findUser.getId());
        deleteUserJourneyPort.deleteAllByUserId(findUser.getId());
        deleteUserPort.deleteUserById(findUser.getId());
        deleteNotificationTokenPort.deleteAllByUserId(findUser.getId());
        //findUser.softDelete();
        //updateUserPort.updateUser(findUser);
        return null;
    }

    @Override
    @Transactional
    public UserReissueResponse reissue(ReissueCommand reissueCommand) {
        Long userId = jwtValidator.validateRefreshToken(reissueCommand.refreshToken());
        Token token = retrieveTokenPort.retrieveTokenById(userId);

        jwtValidator.checkTokenEquality(reissueCommand.refreshToken(), token.getRefreshToken());

        User findUser = retrieveUserPort.getUserById(token.getId());
        TokenResponse issuedTokenResponse = jwtProvider.issueTokens(findUser.getId(), getUserRole(findUser.getId()));
        Token newToken = Token.of(findUser.getId(), issuedTokenResponse.refreshToken());

        updateTokenPort.updateToken(newToken);
        return UserReissueResponse.from(issuedTokenResponse);
    }

    private boolean isRegistered(final Optional<User> user) {
        return user.map(u -> u.getStatus() == ACTIVE)
                .orElse(false);
    }

    private User loadOrCreateUser(final Optional<User> findUser, final UserInfoResponse userInfo) {
        return findUser.orElseGet(() -> createNewUser(userInfo));
    }

    private User createNewUser(final UserInfoResponse userInfo) {
        User newUser = UserMapper.userInfoToDomain(userInfo.serialId(), userInfo.platform(), ERole.USER, userInfo.refreshToken());
        return createUserPort.createUser(newUser);
    }

    private String getUserRole(final long userId) {
        return retrieveUserPort.getUserById(userId).getRole().getValue();
    }

}
