package com.heartz.byeboo.application.service.userblock;

import com.heartz.byeboo.application.command.userblock.UserBlockCommand;
import com.heartz.byeboo.application.port.in.usecase.UserBlockUseCase;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.userblock.CreateUserBlockPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserBlock;
import com.heartz.byeboo.mapper.UserBlockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBlockService implements UserBlockUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final CreateUserBlockPort createUserBlockPort;

    @Override
    public Void block(UserBlockCommand command) {
        User blockerUser = retrieveUserPort.getUserById(command.getUserId());
        User blockedUser = retrieveUserPort.getUserById(command.getTargetUserId());
        UserBlock userBlock = UserBlockMapper.usersToDomain(blockedUser, blockerUser);
        createUserBlockPort.createUserBlock(userBlock);

        //TODO: 사용자 차단 시 차단한 사용자 상호 활동 숨김 처리
        return null;
    }
}
