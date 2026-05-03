package com.heartz.byeboo.application.command.comment;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDeleteCommand{
    Long userId;
    Long commentId;

    public static CommentDeleteCommand of(Long userId, Long commentId){
        return CommentDeleteCommand.builder()
                .userId(userId)
                .commentId(commentId)
                .build();
    }
}
