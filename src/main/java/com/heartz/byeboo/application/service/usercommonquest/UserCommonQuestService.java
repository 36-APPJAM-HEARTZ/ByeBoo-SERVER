package com.heartz.byeboo.application.service.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.MyCommonQuestProjection;
import com.heartz.byeboo.application.command.usercommonquest.*;
import com.heartz.byeboo.application.port.in.dto.response.usercommonquest.*;
import com.heartz.byeboo.application.port.in.usecase.UserCommonQuestUseCase;
import com.heartz.byeboo.core.common.out.port.ProfanityCheckPort;
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
import java.util.function.Function;
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
    private final ProfanityCheckPort profanityCheckPort;

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

        //욕설 포함 여부 체크
        if(profanityCheckPort.containsBadWord(command.getAnswer())){
            throw new CustomException(UserCommonQuestErrorCode.USER_COMMON_QUEST_BAD_WORDS);
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

        //욕설 포함 여부 체크
        if(profanityCheckPort.containsBadWord(command.getAnswer())){
            throw new CustomException(UserCommonQuestErrorCode.USER_COMMON_QUEST_BAD_WORDS);
        }

        userCommonQuest.updateAnswer(command.getAnswer());
        updateUserCommonQuestPort.updateUserCommonQuest(userCommonQuest);

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserCommonQuestListResponseDto getListCommonQuest(CommonQuestListCommand command) {
        validateNotFuture(command.getTargetDate());

        User findUser = retrieveUserPort.getUserById(command.getUserId());
        CommonQuest findCommonQuest = retrieveCommonQuestPort.getCommonQuestByTargetDate(command.getTargetDate());

        // 답변 여부 및 전체 카운트 확인
        boolean isAnswered = isUserAlreadyAnswered(findUser, command.getTargetDate());
        long totalCount = retrieveUserCommonQuestPort.countByCreatedDateBetween(command.getTargetDate());

        //무한 스크롤 데이터 조회 (limit + 1)
        int limitPlusOne = command.getLimit()+1;
        List<UserCommonQuest> userCommonQuestListPlusLimit = retrieveUserCommonQuestPort.getUserCommonQuestsByCreatedDate(command.getTargetDate(), command.getCursor(), limitPlusOne, findCommonQuest);

        //페이징 가공 (데이터 자르기 및 다음 커서 추출)
        boolean hasNext = hasNextData(userCommonQuestListPlusLimit.size(), limitPlusOne); // 11개를 가져왔다면 다음 페이지가 있음
        List<UserCommonQuest> slicedQuestByDate = sliceUnderLimit(hasNext, userCommonQuestListPlusLimit, command.getLimit());
        Long nextCursor = getNextCursor(slicedQuestByDate, UserCommonQuest::getId);

        //작성자 정보 인덱싱 및 매핑
        Map<Long, User> writers = writerIndexingById(extractWriterIds(slicedQuestByDate));
        List<UserCommonQuestDetailResponseDto> userCommonQuestDetailList = mapToDetailUserCommonQuest(slicedQuestByDate, writers);

        return UserCommonQuestListResponseDto.from(
                findCommonQuest.getQuestion(),
                isAnswered,
                totalCount,
                userCommonQuestDetailList,
                hasNext,
                nextCursor
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserCommonQuestResponseDto getDetailCommonQuest(CommonQuestDetailCommand command) {
        retrieveUserPort.getUserById(command.getUserId());

        UserCommonQuest findUserCommonQuest = retrieveUserCommonQuestPort.getUserCommonQuestById(command.getAnswerId());
        CommonQuest findCommonQuest = retrieveCommonQuestPort.getCommonQuestById(findUserCommonQuest.getCommonQuest().getId());
        User findUser = retrieveUserPort.getUserById(findUserCommonQuest.getUser().getId());

        return UserCommonQuestResponseDto.from(findUserCommonQuest, findCommonQuest, findUser);
    }

    @Override
    public MyCommonQuestListResponseDto getMyCommonQuest(MyCommonQuestCommand command) {
        User findUser = retrieveUserPort.getUserById(command.getUserId());

        int limitPlusOne = command.getLimit()+1;
        List<MyCommonQuestProjection> myCommonQuestProjectionList = retrieveUserCommonQuestPort.getMyCommonQuestsByUserId(findUser, command.getCursor(), limitPlusOne);

        boolean hasNext = hasNextData(myCommonQuestProjectionList.size(), limitPlusOne); // 11개를 가져왔다면 다음 페이지가 있음
        List<MyCommonQuestProjection> slicedQuestByDate = sliceUnderLimit(hasNext, myCommonQuestProjectionList, command.getLimit());
        Long nextCursor = getNextCursor(slicedQuestByDate, MyCommonQuestProjection::getAnswerId);

        List<MyCommonQuestResponseDto> myCommonQuestResponseDtoList = slicedQuestByDate.stream().map(
                myCommonQuestProjection -> MyCommonQuestResponseDto.of(myCommonQuestProjection)
                ).toList();

        return MyCommonQuestListResponseDto.from(hasNext, nextCursor, myCommonQuestResponseDtoList);
    }

    private void validateUserCanWriteCommonQuest(CommonQuest commonQuest){
        if (!commonQuest.getTargetDate().isEqual(LocalDate.now())){
            throw new CustomException(UserCommonQuestErrorCode.COMMON_QUEST_NOT_TODAY);
        }
    }

    private boolean hasNextData(int dataSize, int limitPlusOne){
        return dataSize == limitPlusOne;
    }

    private <T> List<T> sliceUnderLimit(boolean hasNext, List<T> list, int realLimit){
        if (hasNext){
            return list.subList(0, realLimit);
        } else {
            return list;
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

    private List<UserCommonQuestDetailResponseDto> mapToDetailUserCommonQuest(List<UserCommonQuest> quests, Map<Long, User> writerMap) {
        return quests.stream()
                .map(userCommonQuest -> {
                    User writer = writerMap.get(userCommonQuest.getUser().getId());
                    return UserCommonQuestDetailResponseDto.from(userCommonQuest, writer);
                })
                .toList();
    }

    private boolean isUserAlreadyAnswered(User targetUser, LocalDate targetDay) {
        if (targetDay.isEqual(LocalDate.now())) {
            return retrieveUserCommonQuestPort.isUserCommonQuestExistsToday(targetUser);
        }
        return true;
    }

    private <T> Long getNextCursor(List<T> list, Function<T, Long> idExtractor) {
        if (list.isEmpty()) {
            return null;
        } else {
            // 마지막 객체를 꺼낸 뒤, 넘겨받은 추출 방식(idExtractor)으로 ID를 뽑아냄
            return idExtractor.apply(list.get(list.size() - 1));
        }
    }

    private void validateNotFuture(LocalDate targetDay){
        if(targetDay.isAfter(LocalDate.now())){
            throw new CustomException(UserCommonQuestErrorCode.USER_COMMON_QUEST_NOT_FUTURE);
        }
    }

}
