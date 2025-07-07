package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QuestErrorCode implements ErrorCode {

    INVALID_QUEST(HttpStatus.BAD_REQUEST, "올바르지 않은 퀘스트 방식입니다."),;
    private final HttpStatus status;
    private final String message;
}
