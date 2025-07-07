package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.RecordingQuestRequestDto;
import com.heartz.byeboo.constants.TextConstant;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import com.heartz.byeboo.utils.TextUtil;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecordingQuestCreateCommand {
    String answer;
    String questEmotionState;
    Long questId;
    Long userId;

    public static RecordingQuestCreateCommand from(RecordingQuestRequestDto recordingQuestRequestDto, Long questId, Long userId) {
        validateAnswerLength(recordingQuestRequestDto.answer());
        try {
            return RecordingQuestCreateCommand.builder()
                    .answer(recordingQuestRequestDto.answer())
                    .questEmotionState(recordingQuestRequestDto.questEmotionState())
                    .questId(questId)
                    .userId(userId)
                    .build();
        } catch(IllegalArgumentException e){
            throw new CustomException(QuestErrorCode.INVALID_QUEST);
        }
    }

    private static void validateAnswerLength(String answer){
        if (TextUtil.lengthWithEmoji(answer) > TextConstant.RECORDING_QUEST_ANSWER_MAX){
            throw new CustomException(QuestErrorCode.RECORDING_ANSWER_TOO_LONG);
        }

        if (TextUtil.lengthWithEmoji(answer) < TextConstant.RECORDING_QUEST_ANSWER_MIN){
            throw new CustomException(QuestErrorCode.RECORDING_ANSWER_TOO_SHORT);
        }
    }
}
