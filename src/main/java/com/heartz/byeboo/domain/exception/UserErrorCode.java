package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USER_NAME_TOO_SHORT(HttpStatus.BAD_REQUEST,"유저 이름은 최소 2자 이상입니다."),
    USER_NAME_TOO_LONG(HttpStatus.BAD_REQUEST,"유저 이름은 최대 5자 이하입니다."),
    INVALID_QUEST_STYLE(HttpStatus.BAD_REQUEST, "올바르지 않은 퀘스트 방식입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
