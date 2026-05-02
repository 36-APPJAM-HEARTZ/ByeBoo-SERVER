package com.heartz.byeboo.domain.exception;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    COMMENT_TOO_LONG(HttpStatus.BAD_REQUEST, "500자 이하로 작성해주세요")
    ;
    private final HttpStatus status;
    private final String message;
}
