package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserBlockErrorCode implements ErrorCode {

    INVALID_TARGET_ID(HttpStatus.BAD_REQUEST,"자기 자신은 차단할 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
