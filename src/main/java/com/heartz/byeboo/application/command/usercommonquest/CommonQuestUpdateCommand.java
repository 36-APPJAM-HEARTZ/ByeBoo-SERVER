package com.heartz.byeboo.application.command.usercommonquest;

import com.heartz.byeboo.adapter.in.web.dto.request.CommonQuestUpdateRequestDto;
import com.heartz.byeboo.constants.QuestConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserCommonQuestErrorCode;
import com.heartz.byeboo.utils.TextUtil;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommonQuestUpdateCommand {
    private Long userId;
    private Long answerId;
    private String answer;

    public static CommonQuestUpdateCommand from(CommonQuestUpdateRequestDto commonQuestUpdateRequestDto, Long answerId, Long userId){
        validateAnswerLength(commonQuestUpdateRequestDto.answer());

        return CommonQuestUpdateCommand.builder()
                .answerId(answerId)
                .userId(userId)
                .answer(commonQuestUpdateRequestDto.answer())
                .build();
    }

    private static void validateAnswerLength(String answer){
        if (TextUtil.lengthWithEmoji(answer) > QuestConstants.COMMON_QUEST_MAX){
            throw new CustomException(UserCommonQuestErrorCode.COMMON_QUEST_TOO_LONG);
        }

        if (TextUtil.lengthWithEmoji(answer) < QuestConstants.COMMON_QUEST_MIN){
            throw new CustomException(UserCommonQuestErrorCode.COMMON_QUEST_TOO_SHORT);
        }
    }
}
