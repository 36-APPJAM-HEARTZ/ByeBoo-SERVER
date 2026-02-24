package com.heartz.byeboo.application.service.userblock;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserBlockInfoProjection;
import com.heartz.byeboo.application.command.userblock.UserBlockCommand;
import com.heartz.byeboo.application.command.userblock.UserBlockDeleteCommand;
import com.heartz.byeboo.application.port.in.dto.response.userblock.UserBlockListResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.userblock.UserBlockResponseDto;
import com.heartz.byeboo.application.port.in.usecase.UserBlockUseCase;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.userblock.CreateUserBlockPort;
import com.heartz.byeboo.application.port.out.userblock.DeleteUserBlockPort;
import com.heartz.byeboo.application.port.out.userblock.RetrieveUserBlockPort;
import com.heartz.byeboo.domain.model.User;
import com.heartz.byeboo.domain.model.UserBlock;
import com.heartz.byeboo.mapper.UserBlockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBlockService implements UserBlockUseCase {

    private final RetrieveUserPort retrieveUserPort;
    private final CreateUserBlockPort createUserBlockPort;
    private final RetrieveUserBlockPort retrieveUserBlockPort;
    private final DeleteUserBlockPort deleteUserBlockPort;

    @Override
    public Void block(UserBlockCommand command) {
        User blockerUser = retrieveUserPort.getUserById(command.getUserId());
        User blockedUser = retrieveUserPort.getUserById(command.getTargetUserId());
        UserBlock userBlock = UserBlockMapper.usersToDomain(blockedUser, blockerUser);
        createUserBlockPort.createUserBlock(userBlock);

        return null;
    }

    @Override
    public UserBlockListResponseDto getUserBlockList(Long userId) {
        User findUser = retrieveUserPort.getUserById(userId);

        List<UserBlockInfoProjection> userBlockInfoProjectionList = retrieveUserBlockPort.getUserBlockInfo(findUser.getId());

        return UserBlockListResponseDto.of(
                userBlockInfoProjectionList.stream().map(
                        userBlockInfoProjection -> UserBlockResponseDto.of(userBlockInfoProjection)
                ).toList()
        );
    }

    @Override
    public Void unblock(UserBlockDeleteCommand command) {
        retrieveUserPort.getUserById(command.getUserId());
        deleteUserBlockPort.deleteUserBlockById(command.getBlockId());
        return null;
    }
}
