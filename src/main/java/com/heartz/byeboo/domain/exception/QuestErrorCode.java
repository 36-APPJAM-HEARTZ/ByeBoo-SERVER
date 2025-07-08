package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QuestErrorCode implements ErrorCode {

    QUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "퀘스트를 찾을 수 없습니다."),
    CURRENT_NUMBER_OVER_MAX(HttpStatus.BAD_REQUEST, "퀘스트는 최대 30번까지만 존재합니다.")
    ;
    private final HttpStatus status;
    private final String message;
}
