package com.heartz.byeboo.application.command.comment;

import com.heartz.byeboo.adapter.in.web.dto.ReplyCreateRequestDto;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyCreateCommand {

    private Long userId;
    private Long commentId;
    private String content;

    public static ReplyCreateCommand of(Long userId, Long commentId, ReplyCreateRequestDto replyCreateRequestDto){
        return ReplyCreateCommand.builder()
                .userId(userId)
                .commentId(commentId)
                .content(replyCreateRequestDto.content())
                .build();
    }
}
