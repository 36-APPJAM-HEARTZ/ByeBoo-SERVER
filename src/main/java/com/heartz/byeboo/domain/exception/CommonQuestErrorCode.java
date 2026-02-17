package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonQuestErrorCode implements ErrorCode {
    COMMON_QUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "공통 퀘스트를 찾을 수 없습니다.")
    ;
    private final HttpStatus status;
    private final String message;
}

