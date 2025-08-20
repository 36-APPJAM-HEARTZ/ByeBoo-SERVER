package com.heartz.byeboo.security.service;

import com.heartz.byeboo.adapter.out.OAuthUserInfoAdapter;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserInfoResponse;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.application.port.out.user.CreateUserPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.user.UpdateUserPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.type.ERole;
import com.heartz.byeboo.domain.type.EUserStatus;
import com.heartz.byeboo.infrastructure.dto.SocialInfoResponse;
import com.heartz.byeboo.mapper.UserMapper;
import com.heartz.byeboo.security.command.OAuthLoginCommand;
import com.heartz.byeboo.security.command.OAuthLogoutCommand;
import com.heartz.byeboo.security.command.OAuthWithdrawCommand;
import com.heartz.byeboo.security.jwt.JwtProvider;
import com.heartz.byeboo.security.jwt.Token;
import com.heartz.byeboo.security.usecase.OAuthUseCase;
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
    private final UpdateUserPort updateUserPort;


    @Transactional
    @Override
    public UserLoginResponse login(OAuthLoginCommand command) {
        SocialInfoResponse response = oAuthUserInfoAdapter.getUserInfo(command.getToken(), command.getPlatform());
        UserInfoResponse userInfoResponse = UserInfoResponse.of(command.getPlatform(), response.serialId());
        Optional<User> user = retrieveUserPort.findUserByPlatFormAndSeralId(userInfoResponse.platform(), userInfoResponse.serialId());
        boolean isRegistered = isRegistered(user);
        User findUser = loadOrCreateUser(user, userInfoResponse);
        Token issuedToken = jwtProvider.issueTokens(findUser.getId(), getUserRole(findUser.getId()));
        return UserLoginResponse.of(issuedToken, isRegistered);
    }

    @Override
    public Void logout(OAuthLogoutCommand command) {
        User findUser = retrieveUserPort.getUserById(command.userId());
        //TODO :  레디스에서 유저의 리프레시 토큰 삭제

        return null;
    }

    @Override
    @Transactional
    public Void withdraw(OAuthWithdrawCommand command) {
        User findUser = retrieveUserPort.getUserById(command.userId());
        oAuthUserInfoAdapter.revoke(findUser.getPlatform(), command.code(), findUser.getSerialId());
        findUser.softDelete();
        updateUserPort.updateUser(findUser);
        return null;
    }

    private boolean isRegistered(final Optional<User> user) {
        return user.map(u -> u.getStatus() == ACTIVE)
                .orElse(false);
    }

    private User loadOrCreateUser(final Optional<User> findUser, final UserInfoResponse userInfo) {
        return findUser.orElseGet(() -> createNewUser(userInfo));
    }

//    private User loadOrCreateUser(final Optional<User> findUser, final UserInfoResponse userInfo) {
//        return findUser.map(user -> updateOrGetUserInfo(user, user.getStatus(), userInfo))
//                .orElseGet(() -> createNewUser(userInfo));
//    }
//
//    private User updateOrGetUserInfo(final User user, final EUserStatus status, final UserInfoResponse userInfo) {
//        if (status == ACTIVE) {
//            return user;
//        }
//        return updateUserInfo(user, userInfo);
//    }
//
//    private User updateUserInfo(final User user) {
//        user.rejoin();
//        return user;
//    }

    private User createNewUser(final UserInfoResponse userInfo) {
        User newUser = UserMapper.userInfoToDomain(userInfo.serialId(), userInfo.platform(), ERole.USER);
        return createUserPort.createUser(newUser);
    }

    private String getUserRole(final long userId) {
        return retrieveUserPort.getUserById(userId).getRole().getValue();
    }

}
