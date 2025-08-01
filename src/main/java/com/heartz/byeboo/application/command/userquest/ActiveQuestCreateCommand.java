package com.heartz.byeboo.application.command.userquest;

import com.heartz.byeboo.adapter.in.web.dto.request.ActiveQuestRequestDto;
import com.heartz.byeboo.constants.QuestConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.domain.type.EQuestEmotionState;
import com.heartz.byeboo.utils.TextUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ActiveQuestCreateCommand {
    private String answer;
    private EQuestEmotionState questEmotionState;
    private UUID imageKey;
    private Long questId;
    private Long userId;

    public static ActiveQuestCreateCommand from(ActiveQuestRequestDto activeQuestRequestDto, Long questId, Long userId) {
        validateAnswerLength(activeQuestRequestDto.answer());
        try {
            return ActiveQuestCreateCommand.builder()
                    .answer(activeQuestRequestDto.answer())
                    .questEmotionState(EQuestEmotionState.valueOf(activeQuestRequestDto.questEmotionState()))
                    .imageKey(activeQuestRequestDto.imageKey())
                    .questId(questId)
                    .userId(userId)
                    .build();
        } catch(IllegalArgumentException e){
            throw new CustomException(UserQuestErrorCode.INVALID_QUEST_EMOTION_STATE);
        }
    }

    private static void validateAnswerLength(String answer){
        if (TextUtil.lengthWithEmoji(answer) > QuestConstants.ACTIVE_QUEST_ANSWER_MAX){
            throw new CustomException(UserQuestErrorCode.ACTIVE_ANSWER_TOO_LONG);
        }
    }
}
