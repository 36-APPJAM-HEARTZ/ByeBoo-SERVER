package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserJourneyErrorCode implements ErrorCode {
    NOT_FOUND_ONGOING_USER_JOURNEY(HttpStatus.NOT_FOUND, "현재 진행중인 여정이 없습니다."),
    CONFLICT_USER_JOURNEY_STATUS(HttpStatus.CONFLICT, "현재 진행중인 여정이 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
