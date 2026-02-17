package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserCommonQuestErrorCode implements ErrorCode {
    COMMON_QUEST_TOO_LONG(HttpStatus.BAD_REQUEST, "공통 퀘스트 답변 길이는 500자 이내입니다. "),
    COMMON_QUEST_TOO_SHORT(HttpStatus.BAD_REQUEST, "공통 퀘스트 답변 길이는 10자 이상입니다."),
    COMMON_QUEST_NOT_TODAY(HttpStatus.BAD_REQUEST, "작성 가능한 날이 아닙니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
