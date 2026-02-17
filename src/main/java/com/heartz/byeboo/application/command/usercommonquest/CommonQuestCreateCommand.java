package com.heartz.byeboo.application.command.usercommonquest;

import com.heartz.byeboo.adapter.in.web.dto.request.CommonQuestCreateRequestDto;
import com.heartz.byeboo.constants.QuestConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserCommonQuestErrorCode;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.utils.TextUtil;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommonQuestCreateCommand {
    private String answer;
    private Long questId;
    private Long userId;

    public static CommonQuestCreateCommand from(CommonQuestCreateRequestDto commonQuestCreateRequestDto, Long questId, Long userId){
        validateAnswerLength(commonQuestCreateRequestDto.answer());
            return CommonQuestCreateCommand.builder()
                    .answer(commonQuestCreateRequestDto.answer())
                    .questId(questId)
                    .userId(userId)
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
