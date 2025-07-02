package com.heartz.byeboo.core.common;

import com.heartz.byeboo.core.exception.ErrorCode;
import lombok.Builder;

public record BaseResponse<T>(
        Boolean success,
        String message,
        T data
) {
    @Builder
    public BaseResponse {
    }

    public static <T> BaseResponse<T> success(final T data) {
        return BaseResponse.<T>builder()
                .success(Boolean.TRUE)
                .message("요청에 대해 정상적으로 처리되었습니다.")
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> fail(ErrorCode e) {
        return BaseResponse.<T>builder()
                .success(Boolean.FALSE)
                .message(e.getMessage())
                .data(null)
                .build();
    }

    public static <T> BaseResponse<T> fail(ErrorCode e, String errorMessage) {
        return BaseResponse.<T>builder()
                .success(Boolean.FALSE)
                .message(errorMessage)
                .data(null)
                .build();
    }
}