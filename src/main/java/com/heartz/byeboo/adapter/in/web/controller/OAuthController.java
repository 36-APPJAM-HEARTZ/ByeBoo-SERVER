package com.heartz.byeboo.adapter.in.web.controller;

import com.heartz.byeboo.adapter.in.web.dto.request.UserLoginRequestDto;
import com.heartz.byeboo.application.command.auth.ReissueCommand;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserLoginResponse;
import com.heartz.byeboo.application.port.in.dto.response.auth.UserReissueResponse;
import com.heartz.byeboo.constants.AuthConstants;
import com.heartz.byeboo.core.annotation.UserId;
import com.heartz.byeboo.core.common.BaseResponse;
import com.heartz.byeboo.application.command.auth.OAuthLoginCommand;
import com.heartz.byeboo.application.command.auth.OAuthLogoutCommand;
import com.heartz.byeboo.application.command.auth.OAuthWithdrawCommand;
import com.heartz.byeboo.application.port.in.usecase.OAuthUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
@Tag(name = "AUTH API", description = "AUTH 대한 API입니다.")
public class OAuthController {

    private final OAuthUseCase oAuthUseCase;

    @Operation(
            summary = "로그인 / 회원가입",
            description = "로그인 / 회원가입을 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "로그인 / 회원가입 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "올바르지 않은 플랫폼"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "토큰 형식이 잘못된 경우"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PostMapping("/login")
    public BaseResponse<UserLoginResponse> login(
            @Parameter(hidden = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @Nullable
            @Schema(description = "널 가능(애플 회원 로그인시에만 요청)")
            @RequestHeader("X-Apple-Code") final String code,
            @RequestBody final UserLoginRequestDto request){
        String rawToken = token.substring(AuthConstants.OAuth2.PREFIX_BEARER.length()).trim();
        return BaseResponse.success(oAuthUseCase.login(OAuthLoginCommand.of(request.platform(), rawToken, code)));
    }

    @Operation(
            summary = "로그아웃",
            description = "로그아웃을 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "로그아웃 성공"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "토큰 형식이 잘못된 경우"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @DeleteMapping("/logout")
    public BaseResponse<Void> signOut(
            @UserId final Long userId
    ) {
        oAuthUseCase.logout(OAuthLogoutCommand.from(userId));
        return BaseResponse.success(oAuthUseCase.logout(OAuthLogoutCommand.from(userId)));
    }

    @Operation(
            summary = "회원 탈퇴",
            description = "회원 탈퇴를 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "회원 탈퇴 성공"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "토큰 형식이 잘못된 경우"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @DeleteMapping("/withdraw")
    public BaseResponse<Void> withdraw(@UserId final Long userId) {
        return BaseResponse.success(oAuthUseCase.withdraw(OAuthWithdrawCommand.of(userId)));
    }

    @Operation(
            summary = "토큰 재발급",
            description = "토큰 재발급을 위한 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "토큰 재발급 성곱"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "올바르지 않은 토큰"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "토큰 형식이 잘못된 경우"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "리프레시 토큰이 일치하지 않는 경우"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @PostMapping("/reissue")
    public BaseResponse<UserReissueResponse> reissue(
            @Parameter(hidden = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String refreshToken
    ) {
        return BaseResponse.success(oAuthUseCase.reissue(ReissueCommand.of(refreshToken)));
    }

}
