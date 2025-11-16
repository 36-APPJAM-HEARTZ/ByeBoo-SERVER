package com.heartz.byeboo.application.command.userquest;

import com.heartz.byeboo.adapter.in.web.dto.request.RecordingQuestUpdateRequestDto;
import com.heartz.byeboo.constants.QuestConstants;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserQuestErrorCode;
import com.heartz.byeboo.utils.TextUtil;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecordingQuestUpdateCommand {
    private String answer;
    private Long questId;
    private Long userId;

    public static RecordingQuestUpdateCommand from(RecordingQuestUpdateRequestDto recordingQuestRequestDto, Long questId, Long userId) {
        validateAnswerLength(recordingQuestRequestDto.answer());
        try {
            return RecordingQuestUpdateCommand.builder()
                    .answer(recordingQuestRequestDto.answer())
                    .questId(questId)
                    .userId(userId)
                    .build();
        } catch(IllegalArgumentException e){
            throw new CustomException(UserQuestErrorCode.INVALID_QUEST_EMOTION_STATE);
        }
    }

    private static void validateAnswerLength(String answer){
        if (TextUtil.lengthWithEmoji(answer) > QuestConstants.RECORDING_QUEST_ANSWER_MAX){
            throw new CustomException(UserQuestErrorCode.RECORDING_ANSWER_TOO_LONG);
        }

        if (TextUtil.lengthWithEmoji(answer) < QuestConstants.RECORDING_QUEST_ANSWER_MIN){
            throw new CustomException(UserQuestErrorCode.RECORDING_ANSWER_TOO_SHORT);
        }
    }

}
