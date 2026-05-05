package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.usercommonquest.*;
import com.heartz.byeboo.application.port.in.dto.response.usercommonquest.*;

public interface UserCommonQuestUseCase {
    Void createCommonQuest(CommonQuestCreateCommand command);
    Void deleteCommonQuest(CommonQuestDeleteCommand command);
    Void updateCommonQuest(CommonQuestUpdateCommand command);
    UserCommonQuestListResponseDto getListCommonQuest(CommonQuestListCommand command);
    UserCommonQuestResponseDto getDetailCommonQuest(CommonQuestDetailCommand command);
    MyCommonQuestListResponseDto getMyCommonQuest(MyCommonQuestCommand command);
    LikeResponseDto like(LikeCreateCommand command);
    UserCommonQuestResponseV2Dto getDetailCommonQuestV2(CommonQuestDetailCommand command);
}
