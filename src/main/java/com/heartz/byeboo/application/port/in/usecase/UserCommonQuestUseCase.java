package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.usercommonquest.*;
import com.heartz.byeboo.application.port.in.dto.response.usercommonquest.MyCommonQuestListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.usercommonquest.UserCommonQuestListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.usercommonquest.UserCommonQuestResponseDto;

import java.util.List;

public interface UserCommonQuestUseCase {
    Void createCommonQuest(CommonQuestCreateCommand command);
    Void deleteCommonQuest(CommonQuestDeleteCommand command);
    Void updateCommonQuest(CommonQuestUpdateCommand command);
    UserCommonQuestListResponseDto getListCommonQuest(CommonQuestListCommand command);
    UserCommonQuestResponseDto getDetailCommonQuest(CommonQuestDetailCommand command);
    MyCommonQuestListResponseDto getMyCommonQuest(MyCommonQuestCommand command);
}
