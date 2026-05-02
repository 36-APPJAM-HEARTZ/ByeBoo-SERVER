package com.heartz.byeboo.application.command.comment;

import com.heartz.byeboo.adapter.in.web.dto.request.CommentCreateRequestDto;
import com.heartz.byeboo.constants.CommentConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.CommentErrorCode;
import com.heartz.byeboo.utils.TextUtil;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentCreateCommand {
    private Long userId;
    private Long targetId;
    private String content;

    public static CommentCreateCommand of(Long userId, CommentCreateRequestDto commentCreateRequestDto){
        validateCommentLength(commentCreateRequestDto.content());

        return CommentCreateCommand.builder()
                .userId(userId)
                .targetId(commentCreateRequestDto.targetId())
                .content(commentCreateRequestDto.content())
                .build();
    }

    private static void validateCommentLength(String content){
        if (TextUtil.lengthWithEmoji(content) > CommentConstants.COMMENT_CONTENT_MAX){
            throw new CustomException(CommentErrorCode.COMMENT_TOO_LONG);
        }
    }
}
