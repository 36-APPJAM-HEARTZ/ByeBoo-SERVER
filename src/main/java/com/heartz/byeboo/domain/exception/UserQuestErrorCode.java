package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserQuestErrorCode implements ErrorCode {

    INVALID_QUEST_PROGRESS(HttpStatus.BAD_REQUEST, "현재 진행 중인 퀘스트가 아닙니다."),
    INVALID_QUEST_EMOTION_STATE(HttpStatus.BAD_REQUEST, "퀘스트 감정상태가 올바르지 않습니다."),
    RECORDING_ANSWER_TOO_LONG(HttpStatus.BAD_REQUEST, "질문형 퀘스트 답변 길이는 500자 이내입니다. "),
    RECORDING_ANSWER_TOO_SHORT(HttpStatus.BAD_REQUEST, "질문형 퀘스트 답변 길이는 10자 이상입니다."),
    ACTIVE_ANSWER_TOO_LONG(HttpStatus.BAD_REQUEST, "행동형 퀘스트 답변 길이는 200자 이내입니다. "),
    IMAGE_NOT_UPLOADED(HttpStatus.NOT_FOUND, "버킷에 이미지가 업로드되지 않았습니다."),
    USER_QUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 퀘스트가 존재하지 않습니다."),
    NOT_FOUND_ONGOING_USER_QUEST(HttpStatus.NOT_FOUND, "현재 진행중인 퀘스트가 없습니다."),
    INVALID_SIGNED_URL(HttpStatus.BAD_REQUEST, "UUID 형식이 올바르지 않습니다."),
    NOT_USER_QUEST_STYLE(HttpStatus.BAD_REQUEST, "옳지 않은 퀘스트 스타일입니다."),
    RECENT_USER_QUEST_NOT_MATCHED(HttpStatus.CONFLICT, "최근 저장된 퀘스트가 진행되었어야하는 퀘스트와 다릅니다.")
    ;
    private final HttpStatus status;
    private final String message;

}
