package com.heartz.byeboo.application.command;

import com.heartz.byeboo.adapter.in.web.dto.RecordingQuestRequestDto;
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
        return RecordingQuestCreateCommand.builder()
                .answer(recordingQuestRequestDto.answer())
                .questEmotionState(recordingQuestRequestDto.questEmotionState())
                .questId(questId)
                .userId(userId)
                .build();
    }
}
