package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.ActiveQuestRequestDto;
import com.heartz.byeboo.constants.TextConstant;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import com.heartz.byeboo.domain.type.EQuestEmotionState;
import com.heartz.byeboo.utils.TextUtil;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ActiveQuestCreateCommand {
    String answer;
    EQuestEmotionState questEmotionState;
    Long questId;
    Long userId;

    public static ActiveQuestCreateCommand from(ActiveQuestRequestDto activeQuestRequestDto, Long questId, Long userId) {
        validateAnswerLength(activeQuestRequestDto.answer());
        try {
            return ActiveQuestCreateCommand.builder()
                    .answer(activeQuestRequestDto.answer())
                    .questEmotionState(EQuestEmotionState.valueOf(activeQuestRequestDto.questEmotionState()))
                    .questId(questId)
                    .userId(userId)
                    .build();
        } catch(IllegalArgumentException e){
            throw new CustomException(QuestErrorCode.INVALID_QUEST);
        }
    }

    private static void validateAnswerLength(String answer){
        if (TextUtil.lengthWithEmoji(answer) > TextConstant.ACTIVE_QUEST_ANSWER_MAX){
            throw new CustomException(QuestErrorCode.ACTIVE_ANSWER_TOO_LONG);
        }
    }
}
