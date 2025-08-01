package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserJourneyErrorCode implements ErrorCode {
    NOT_FOUND_ONGOING_USER_JOURNEY(HttpStatus.NOT_FOUND, "현재 진행중인 여정이 없습니다."),
    CONFLICT_USER_JOURNEY_STATUS(HttpStatus.CONFLICT, "이미 진행중인 여정입니다."),
    CONFLICT_USER_JOURNEY(HttpStatus.CONFLICT, "이미 진행 완료된 여정입니다."),
    INVALID_USER_JOURNEY(HttpStatus.BAD_REQUEST, "올바르지 않은 여정 형식입니다."),
    INVALID_JOURNEY_TYPE(HttpStatus.BAD_REQUEST, "올바르지 않은 여정입니다."),
    NOT_FOUND_USER_JOURNEY(HttpStatus.NOT_FOUND, "찾고자 하는 여정이 존재하지 않습니다."),
    INVALID_COMPLETED_USER_JOURNEY(HttpStatus.BAD_REQUEST, "완료된 여정이 아닙니다."),
    BEFORE_START_USER_JOURNEY(HttpStatus.BAD_REQUEST, "시작 전 여정입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
