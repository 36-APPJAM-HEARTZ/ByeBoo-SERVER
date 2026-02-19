package com.heartz.byeboo.application.service.usercommonquest;

import com.heartz.byeboo.application.command.usercommonquest.CommonQuestCreateCommand;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestDeleteCommand;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestListCommand;
import com.heartz.byeboo.application.command.usercommonquest.CommonQuestUpdateCommand;
import com.heartz.byeboo.application.port.in.dto.response.usercommonquest.UserCommonQuestDetailResponseDto;
import com.heartz.byeboo.application.port.in.dto.response.usercommonquest.UserCommonQuestListResponseDto;
import com.heartz.byeboo.application.port.in.usecase.UserCommonQuestUseCase;
import com.heartz.byeboo.application.port.out.commonquest.RetrieveCommonQuestPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.usercommonquest.CreateUserCommonQuestPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.application.port.out.usercommonquest.UpdateUserCommonQuestPort;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCommonQuestService implements UserCommonQuestUseCase {

    private final CreateUserCommonQuestPort createUserCommonQuestPort;
    private final RetrieveUserPort retrieveUserPort;
    private final RetrieveCommonQuestPort retrieveCommonQuestPort;
    private final RetrieveUserCommonQuestPort retrieveUserCommonQuestPort;
    private final UpdateUserCommonQuestPort updateUserCommonQuestPort;

    @Override
    @Transactional
    public Void createCommonQuest(CommonQuestCreateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        CommonQuest findCommonQuest = retrieveCommonQuestPort.getCommonQuestById(command.getQuestId());

        validateUserCanWriteCommonQuest(findCommonQuest); //퀘스트 날짜가 오늘인지

        //유저가 오늘 작성했는지 여부 체크
        if(retrieveUserCommonQuestPort.isUserCommonQuestExistsToday(findUser)){
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

    @Override
    @Transactional
    public Void updateCommonQuest(CommonQuestUpdateCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        UserCommonQuest userCommonQuest = retrieveUserCommonQuestPort.getUserCommonQuestByUserAndId(findUser, command.getAnswerId());

        userCommonQuest.updateAnswer(command.getAnswer());
        updateUserCommonQuestPort.updateUserCommonQuest(userCommonQuest);

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserCommonQuestListResponseDto getListCommonQuest(CommonQuestListCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());
        boolean isAnswered = isUserAlreadyAnswered(findUser, command.getTargetDate());

        CommonQuest findCommonQuest = retrieveCommonQuestPort.getCommonQuestByTargetDate(command.getTargetDate());

        int limitPlusOne = command.getLimit()+1;
        List<UserCommonQuest> userCommonQuestListPlusLimit = retrieveUserCommonQuestPort.getUserCommonQuestsByCreatedDate(command.getTargetDate(), command.getCursor(), limitPlusOne, findCommonQuest);

        boolean hasNext = hasNextData(userCommonQuestListPlusLimit.size(), limitPlusOne); // 11개를 가져왔다면 다음 페이지가 있음
        List<UserCommonQuest> slicedQuestByDate = sliceUnderLimit(hasNext, userCommonQuestListPlusLimit, command.getLimit());

        long totalCont = retrieveUserCommonQuestPort.countByCreatedDateBetween(command.getTargetDate());
        Long nextCursor = getNextCursor(slicedQuestByDate);

        //TODO : id가져오기 메서드 분리
        List<Long> userIds = slicedQuestByDate.stream().map(q -> q.getUser().getId()).toList();
        Map<Long, User> writers = writerIndexingById(userIds);

        return UserCommonQuestListResponseDto.from(
                findCommonQuest.getQuestion(),
                isAnswered,
                totalCont,

        )

    }

    private void validateUserCanWriteCommonQuest(CommonQuest commonQuest){
        if (!commonQuest.getTargetDate().isEqual(LocalDate.now())){
            throw new CustomException(UserCommonQuestErrorCode.COMMON_QUEST_NOT_TODAY);
        }
    }

    private boolean hasNextData(int dataSize, int limitPlusOne){
        return dataSize == limitPlusOne;
    }

    private List<UserCommonQuest> sliceUnderLimit(boolean hasNext, List<UserCommonQuest> userCommonQuestListPlusLimit, int realLimit){
        if (hasNext){
            return userCommonQuestListPlusLimit.subList(0, realLimit);
        } else {
            return userCommonQuestListPlusLimit;
        }
    }

    // 유저 리스트 -> map<>을 활용해 id값으로 인덱싱
    private Map<Long, User> writerIndexingById(List<Long> writerIds) {
        if (writerIds.isEmpty()) return Collections.emptyMap();
        return retrieveUserPort.findUsersById(writerIds).stream()
                .collect(Collectors.toMap(User::getId, user -> user));
    }

    private List<Long> extractWriterIds(List<UserCommonQuest> quests) {
        return quests.stream()
                .map(q -> q.getUser().getId())
                .distinct()
                .toList();
    }

    //TODO : UserCommonQuestDetailResponseDto로 변환
    private List<UserCommonQuestDetailResponseDto> mapToAnswerItems(List<UserCommonQuest> quests, Map<Long, User> writerMap) {
        return quests.stream()
                .map(q -> {
                    User writer = writerMap.get(q.getUser().getId());
                    return UserCommonQuestDetailResponseDto.(q, writer);
                })
                .toList();
    }

    private boolean isUserAlreadyAnswered(User targetUser, LocalDate targetDay){
        if (targetDay.isEqual(LocalDate.now())){
            retrieveUserCommonQuestPort.isUserCommonQuestExistsToday(targetUser);
            return false;
        } else {
            return true;
        }
    }

    private Long getNextCursor(List<UserCommonQuest> slicedQuestByDate) {
        if (slicedQuestByDate.isEmpty()){
            return null;
        } else {
            return slicedQuestByDate.get(slicedQuestByDate.size() - 1).getId();
        }
    }

}
