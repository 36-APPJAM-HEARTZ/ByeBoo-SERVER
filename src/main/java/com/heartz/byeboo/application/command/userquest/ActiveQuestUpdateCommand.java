package com.heartz.byeboo.application.command.userquest;

import com.heartz.byeboo.adapter.in.web.dto.request.ActiveQuestUpdateRequestDto;
import com.heartz.byeboo.constants.QuestConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.utils.TextUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ActiveQuestUpdateCommand {
    private String answer;
    private UUID imageKey;
    private Long questId;
    private Long userId;

    public static ActiveQuestUpdateCommand from(ActiveQuestUpdateRequestDto activeQuestCreateRequestDto, Long questId, Long userId) {
        validateAnswerLength(activeQuestCreateRequestDto.answer());
        try {
            return ActiveQuestUpdateCommand.builder()
                    .answer(activeQuestCreateRequestDto.answer())
                    .imageKey(activeQuestCreateRequestDto.imageKey())
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
