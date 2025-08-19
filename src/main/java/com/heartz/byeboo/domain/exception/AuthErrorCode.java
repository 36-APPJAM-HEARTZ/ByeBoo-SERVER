package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "리소스 접근 권한이 없습니다."),
    INVALID_PLATFORM(HttpStatus.UNAUTHORIZED,"올바르지 않은 로그인 방식입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰 형식입니다."),
    INVALID_ACCESS_TOKEN_VALUE(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰 값입니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료되었습니다."),
    INVALID_REFRESH_TOKEN_VALUE(HttpStatus.UNAUTHORIZED, "리프레시 토큰의 값이 올바르지 않습니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 만료되었습니다."),
    MISMATCH_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 일치하지 않습니다."),
    MISSING_BEARER_PREFIX(HttpStatus.UNAUTHORIZED, "Bearer가 누락되었습니다.");


    private final HttpStatus status;
    private final String message;
}
