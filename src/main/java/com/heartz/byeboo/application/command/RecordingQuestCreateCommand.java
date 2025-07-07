package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.RecordingQuestRequestDto;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.QuestErrorCode;
import lombok.Builder;

@Builder
public class RecordingQuestCreateCommand {
    String answer;
    String questEmotionState;

    public static RecordingQuestCreateCommand from(RecordingQuestRequestDto recordingQuestRequestDto){
        try {
            return RecordingQuestCreateCommand.builder()
                    .answer(recordingQuestRequestDto.answer())
                    .questEmotionState(recordingQuestRequestDto.questEmotionState())
                    .build();
        } catch (IllegalArgumentException e) {
            throw new CustomException(QuestErrorCode.INVALID_QUEST);
        }
    }
}
