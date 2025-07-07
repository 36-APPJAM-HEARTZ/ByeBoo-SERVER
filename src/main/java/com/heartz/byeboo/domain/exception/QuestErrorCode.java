package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QuestErrorCode implements ErrorCode {

    INVALID_QUEST_PROGRESS(HttpStatus.BAD_REQUEST, "현재 진행 중인 퀘스트가 아닙니다."),
    QUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "퀘스트를 찾을 수 없습니다."),
    INVALID_QUEST(HttpStatus.BAD_REQUEST, "퀘스트 형식이 올바르지 않습니다."),
    RECORDING_ANSWER_TOO_LONG(HttpStatus.BAD_REQUEST, "질문형 퀘스트 답변 길이는 500자 이내입니다. "),
    RECORDING_ANSWER_TOO_SHORT(HttpStatus.BAD_REQUEST, "질문형 퀘스트 답변 길이는 10자 이상 입니다.");
    private final HttpStatus status;
    private final String message;
}
