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
    COMMON_QUEST_NOT_TODAY(HttpStatus.BAD_REQUEST, "작성 가능한 날이 아닙니다."),
    COMMON_QUEST_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 작성 완료 했습니다."),
    USER_COMMON_QUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 공통 퀘스트가 존재하지 않습니다."),
    USER_COMMON_QUEST_NOT_FUTURE(HttpStatus.BAD_REQUEST, "오늘 날짜 이후의 날짜에 대해서는 조회할 수 없습니다."),
    USER_COMMON_QUEST_BAD_WORDS(HttpStatus.BAD_REQUEST, "답변에 욕설을 포함하고 있습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
