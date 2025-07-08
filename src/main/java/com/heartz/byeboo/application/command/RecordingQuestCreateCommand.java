package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.request.RecordingQuestRequestDto;
import com.heartz.byeboo.constants.TextConstant;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.type.EQuestEmotionState;
import com.heartz.byeboo.utils.TextUtil;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecordingQuestCreateCommand {
    private String answer;
    private EQuestEmotionState questEmotionState;
    private Long questId;
    private Long userId;

    public static RecordingQuestCreateCommand from(RecordingQuestRequestDto recordingQuestRequestDto, Long questId, Long userId) {
        validateAnswerLength(recordingQuestRequestDto.answer());
        try {
            return RecordingQuestCreateCommand.builder()
                    .answer(recordingQuestRequestDto.answer())
                    .questEmotionState(EQuestEmotionState.valueOf(recordingQuestRequestDto.questEmotionState()))
                    .questId(questId)
                    .userId(userId)
                    .build();
        } catch(IllegalArgumentException e){
            throw new CustomException(UserQuestErrorCode.INVALID_QUEST_EMOTION_STATE);
        }
    }

    private static void validateAnswerLength(String answer){
        if (TextUtil.lengthWithEmoji(answer) > TextConstant.RECORDING_QUEST_ANSWER_MAX){
            throw new CustomException(UserQuestErrorCode.RECORDING_ANSWER_TOO_LONG);
        }

        if (TextUtil.lengthWithEmoji(answer) < TextConstant.RECORDING_QUEST_ANSWER_MIN){
            throw new CustomException(UserQuestErrorCode.RECORDING_ANSWER_TOO_SHORT);
        }
    }

}
