package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QuestErrorCode implements ErrorCode {

    INVALID_QUEST_PROGRESS(HttpStatus.BAD_REQUEST, "현재 진행 중인 퀘스트가 아닙니다."),
    QUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "퀘스트를 찾을 수 없습니다.");
    private final HttpStatus status;
    private final String message;
}
