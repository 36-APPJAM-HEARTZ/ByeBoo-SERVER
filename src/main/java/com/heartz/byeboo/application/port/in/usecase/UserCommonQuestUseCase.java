package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.usercommonquest.CommonQuestCreateCommand;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestDeleteCommand;

public interface UserCommonQuestUseCase {
    Void createCommonQuest(CommonQuestCreateCommand command);
    Void deleteCommonQuest(CommonQuestDeleteCommand command);
}
