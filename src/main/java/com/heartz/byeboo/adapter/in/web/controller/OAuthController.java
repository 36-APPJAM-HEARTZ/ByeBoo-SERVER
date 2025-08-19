package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.UserLoginRequestDto;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.constants.AuthConstants;
import com.heartz.byeboo.core.common.BaseResponse;
import com.heartz.byeboo.security.command.OAuthCommand;
import com.heartz.byeboo.security.usecase.OAuthUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
@Tag(name = "AUTH API", description = "AUTH 대한 API입니다.")
public class OAuthController {

    private final OAuthUseCase oAuthUseCase;

    @PostMapping("/login")
    public BaseResponse<UserLoginResponse> login(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @RequestBody final UserLoginRequestDto request){
        String rawToken = token.substring(AuthConstants.PREFIX_BEARER.length()).trim();
        return BaseResponse.success(oAuthUseCase.login(OAuthCommand.of(request.platform(), rawToken)));
    }
}
