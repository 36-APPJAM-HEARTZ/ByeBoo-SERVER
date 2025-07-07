package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.RecordingQuestRequestDto;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
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
        try {
            return RecordingQuestCreateCommand.builder()
                    .answer(recordingQuestRequestDto.answer())
                    .questEmotionState(recordingQuestRequestDto.questEmotionState())
                    .questId(questId)
                    .userId(userId)
                    .build();
        } catch (IllegalArgumentException e){
            throw new CustomException(QuestErrorCode.INVALID_QUEST);
        }
    }
}
