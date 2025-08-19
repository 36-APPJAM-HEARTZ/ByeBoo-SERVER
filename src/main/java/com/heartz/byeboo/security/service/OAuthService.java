package com.heartz.byeboo.security.service;

import com.heartz.byeboo.adapter.out.OAuthUserInfoAdapter;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserInfoResponse;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.application.port.out.user.CreateUserPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.type.ERole;
import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;
import com.heartz.byeboo.mapper.UserMapper;
import com.heartz.byeboo.security.command.OAuthCommand;
import com.heartz.byeboo.security.jwt.JwtProvider;
import com.heartz.byeboo.security.jwt.Token;
import com.heartz.byeboo.security.usecase.OAuthUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService implements OAuthUseCase {

    private final OAuthUserInfoAdapter oAuthUserInfoAdapter;
    private final RetrieveUserPort retrieveUserPort;
    private final CreateUserPort createUserPort;
    private final JwtProvider jwtProvider;


    @Transactional
    public UserLoginResponse login(OAuthCommand command) {
        SocialInfoResponse response = oAuthUserInfoAdapter.getUserInfo(command.getToken(), command.getPlatform());
        UserInfoResponse userInfoResponse = UserInfoResponse.of(command.getPlatform(), response.serialId());
        Optional<User> user = retrieveUserPort.findUserByPlatFormAndSeralId(userInfoResponse.platform(), userInfoResponse.serialId());
        boolean isRegistered = user.isPresent();
        User findUser = loadOrCreateUser(user, userInfoResponse);
        Token issuedToken = jwtProvider.issueTokens(findUser.getId(), getUserRole(findUser.getId()));
        return UserLoginResponse.of(issuedToken, isRegistered);
    }

    private User loadOrCreateUser(final Optional<User> findUser, final UserInfoResponse userInfo) {
        return findUser.orElseGet(() -> createNewUser(userInfo));
    }

    private User createNewUser(final UserInfoResponse userInfo) {
        User newUser = UserMapper.userInfoToDomain(userInfo.serialId(), userInfo.platform(), ERole.USER);
        return createUserPort.createUser(newUser);
    }

    private String getUserRole(final long userId) {
        return retrieveUserPort.getUserById(userId).getRole().getValue();
    }

}
