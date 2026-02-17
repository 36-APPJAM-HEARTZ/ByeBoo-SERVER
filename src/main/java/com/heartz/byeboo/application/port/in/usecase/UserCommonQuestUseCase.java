package com.heartz.byeboo.application.port.in.usecase;

import com.heartz.byeboo.application.command.usercommonquest.CommonQuestCreateCommand;

public interface UserCommonQuestUseCase {
    Void createCommonQuest(CommonQuestCreateCommand command);
}
