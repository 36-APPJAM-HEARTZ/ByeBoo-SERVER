package com.heartz.byeboo.application.command.comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReplyListCommand {
    private Long userId;
    private Long commentId;

    public static ReplyListCommand of(Long userId, Long commentId){
        return ReplyListCommand.builder()
                .userId(userId)
                .commentId(commentId)
                .build();
    }
}
