package com.heartz.byeboo.application.command.comment;

import com.heartz.byeboo.adapter.in.web.dto.request.CommentUpdateRequestDto;
import com.heartz.byeboo.application.command.quest.AllQuestProgressCommand;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentUpdateCommand {
    private String content;
    private Long userId;
    private Long commentId;

    public static CommentUpdateCommand of(Long userId, CommentUpdateRequestDto commentUpdateRequestDto, Long commentId) {
        return CommentUpdateCommand.builder()
                .userId(userId)
                .commentId(commentId)
                .content(commentUpdateRequestDto.content())
                .build();
    }
}
