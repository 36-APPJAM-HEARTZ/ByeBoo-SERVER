package com.heartz.byeboo.application.service.usercommonquest;

import com.heartz.byeboo.application.command.usercommonquest.CommonQuestCreateCommand;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestDeleteCommand;
import com.heartz.byeboo.application.port.in.usecase.UserCommonQuestUseCase;
import com.heartz.byeboo.application.port.out.commonquest.RetrieveCommonQuestPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.usercommonquest.CreateUserCommonQuestPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserCommonQuestErrorCode;
import com.heartz.byeboo.domain.model.CommonQuest;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserCommonQuest;
import com.heartz.byeboo.mapper.UserCommonQuestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCommonQuestService implements UserCommonQuestUseCase {

    private final CreateUserCommonQuestPort createUserCommonQuestPort;
    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveCommonQuestPort retrieveCommonQuestPort;
    private final RetrieveUserCommonQuestPort retrieveUserCommonQuestPort;

    @Override
    @Transactional
    public Void createCommonQuest(CommonQuestCreateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        CommonQuest findCommonQuest = retrieveCommonQuestPort.getCommonQuestById(command.getQuestId());

        validateUserCanWriteCommonQuest(findCommonQuest);

        //유저가 오늘 작성했는지 여부 체크
        if(retrieveUserCommonQuestPort.isUserCommonQuestExists(findUser)){
            throw new CustomException(UserCommonQuestErrorCode.COMMON_QUEST_ALREADY_EXIST);
        }

        UserCommonQuest userCommonQuest = UserCommonQuestMapper.commandToDomain(command, findUser, findCommonQuest);
        createUserCommonQuestPort.createUserCommonQuest(userCommonQuest);

        return null;
    }

    @Override
    @Transactional
    public Void deleteCommonQuest(CommonQuestDeleteCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        retrieveUserCommonQuestPort.deleteByUserIdAndId(command.getAnswerId(), findUser);

        return null;
    }

    private void validateUserCanWriteCommonQuest(CommonQuest commonQuest){
        if (!commonQuest.getTargetDate().isEqual(LocalDate.now())){
            throw new CustomException(UserCommonQuestErrorCode.COMMON_QUEST_NOT_TODAY);
        }
    }


}
