package com.heartz.byeboo.application.port.in.dto.response.comment;

import java.util.List;

public record ReplyListResponseDto(
    int totalCount,
    CommentResponseDto comment,
    List<ReplyResponseDto> replies
) {
    public static ReplyListResponseDto of(int totalCount, CommentResponseDto commentResponseDto, List<ReplyResponseDto> replies){
        return new ReplyListResponseDto(totalCount, commentResponseDto, replies);
    }
}
