package com.heartz.byeboo.application.service.usercommonquest;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.*;
import com.heartz.byeboo.application.command.usercommonquest.*;
import com.heartz.byeboo.application.port.in.dto.response.usercommonquest.*;
import com.heartz.byeboo.application.port.in.usecase.UserCommonQuestUseCase;
import com.heartz.byeboo.application.port.out.comment.DeleteCommentPort;
import com.heartz.byeboo.application.port.out.comment.RetrieveCommentPort;
import com.heartz.byeboo.application.port.out.like.CreateLikePort;
import com.heartz.byeboo.application.port.out.like.DeleteLikePort;
import com.heartz.byeboo.application.port.out.like.RetrieveLikePort;
import com.heartz.byeboo.application.port.out.notification.CreateNotificationPort;
import com.heartz.byeboo.core.common.out.port.ProfanityCheckPort;
import com.heartz.byeboo.application.port.out.commonquest.RetrieveCommonQuestPort;
import com.heartz.byeboo.application.port.out.user.RetrieveUserPort;
import com.heartz.byeboo.application.port.out.usercommonquest.CreateUserCommonQuestPort;
import com.heartz.byeboo.application.port.out.usercommonquest.RetrieveUserCommonQuestPort;
import com.heartz.byeboo.application.port.out.usercommonquest.UpdateUserCommonQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.UserCommonQuestErrorCode;
import com.heartz.byeboo.domain.model.*;
import com.heartz.byeboo.domain.type.ENotificationType;
import com.heartz.byeboo.mapper.LikeMapper;
import com.heartz.byeboo.mapper.NotificationMapper;
import com.heartz.byeboo.mapper.UserCommonQuestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

import static com.heartz.byeboo.constants.CommonQuestConstants.COMMON_QUEST_CYCLE_SIZE;
import static com.heartz.byeboo.constants.CommonQuestConstants.COMMON_QUEST_START_DATE;

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
    private final DeleteCommentPort deleteCommentPort;
    private final CreateLikePort createLikePort;
    private final RetrieveLikePort retrieveLikePort;
    private final DeleteLikePort deleteLikePort;
    private final RetrieveCommentPort retrieveCommentPort;
    private final CreateNotificationPort createNotificationPort;

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

        deleteCommentPort.deleteAllByUserCommonQuestId(command.getAnswerId());
        //TODO: 대댓글 삭제
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

        LocalDate targetDate = command.getTargetDate();
        int sequence = calculateCommonQuestSequence(targetDate);

        CommonQuest findCommonQuest =
                retrieveCommonQuestPort.getCommonQuestBySequence(sequence);

        // 답변 여부 및 전체 카운트 확인
        boolean isAnswered = isUserAlreadyAnswered(findUser, command.getTargetDate());

        long totalCount = retrieveUserCommonQuestPort.countByCreatedDateBetween(
                targetDate,
                findUser.getId()
        );

        // 무한 스크롤 데이터 조회 limit + 1
        int limitPlusOne = command.getLimit() + 1;

        List<UserCommonQuestInfoProjection> userCommonQuestInfoProjectionListPlusLimit =
                retrieveUserCommonQuestPort.getUserCommonQuestsByCreatedDate(
                        targetDate,
                        command.getCursor(),
                        limitPlusOne,
                        findUser.getId()
                );

        // 페이징 가공
        boolean hasNext = hasNextData(
                userCommonQuestInfoProjectionListPlusLimit.size(),
                limitPlusOne
        );

        List<UserCommonQuestInfoProjection> slicedQuestByDate =
                sliceUnderLimit(
                        hasNext,
                        userCommonQuestInfoProjectionListPlusLimit,
                        command.getLimit()
                );

        Long nextCursor = getNextCursor(
                slicedQuestByDate,
                UserCommonQuestInfoProjection::getAnswerId
        );

        // dto 변환
        List<UserCommonQuestDetailResponseDto> userCommonQuestDetailList =
                slicedQuestByDate.stream()
                        .map(UserCommonQuestDetailResponseDto::of)
                        .toList();

        return UserCommonQuestListResponseDto.from(
                findCommonQuest.getQuestion(),
                isAnswered,
                totalCount,
                userCommonQuestDetailList,
                hasNext,
                nextCursor,
                findCommonQuest.getId()
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

    @Override
    @Transactional
    public LikeResponseDto like(LikeCreateCommand command) {
        retrieveUserPort.validateUserExists(command.getUserId());
        UserCommonQuest userCommonQuest = retrieveUserCommonQuestPort.getUserCommonQuestById(command.getUserCommonQuestId());

        boolean alreadyLiked = retrieveLikePort.existsByUserIdAndUserCommonQuestId(
                command.getUserId(),
                command.getUserCommonQuestId()
        );

        boolean isLiked = toggleLike(command, alreadyLiked);

        int likeCount = retrieveLikePort.countByUserCommonQuestId(
                command.getUserCommonQuestId()
        );

        Notification notification = NotificationMapper.toDomain(userCommonQuest.getUser().getId(), command.getUserCommonQuestId(), ENotificationType.LIKE, command.getUserId());
        createNotificationPort.create(notification);

        return new LikeResponseDto(likeCount, isLiked);
    }

    @Override
    public UserCommonQuestResponseV2Dto getDetailCommonQuestV2(CommonQuestDetailCommand command) {
        retrieveUserPort.validateUserExists(command.getUserId());

        UserCommonQuestDetailProjection detail =
                retrieveUserCommonQuestPort.getUserCommonQuestWithWriter(command.getAnswerId());

        int likeCount =
                retrieveLikePort.countByUserCommonQuestId(command.getAnswerId());

        boolean isLiked =
                retrieveLikePort.existsByUserIdAndUserCommonQuestId(
                        command.getUserId(),
                        command.getAnswerId()
                );

        List<UserCommonQuestCommentListProjection> comments =
                retrieveCommentPort.getCommentsByUserCommonQuestId(command.getAnswerId());

        List<CommentResponseDto> commentResponses = comments.stream()
                .map(CommentResponseDto::of)
                .toList();

        return UserCommonQuestResponseV2Dto.of(
                detail.getQuestion(),
                UserCommonQuestAnswerResponseDto.of(detail.getWriter(), detail.getProfileIcon().name(), detail.getWrittenAt(), detail.getContent(), detail.getWriterId(), likeCount, isLiked, commentResponses.size()),
                commentResponses
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserCommonQuestListResponseV2Dto getListCommonQuestV2(CommonQuestListCommand command) {
        validateNotFuture(command.getTargetDate());

        User findUser = retrieveUserPort.getUserById(command.getUserId());

        LocalDate targetDate = command.getTargetDate();

        int sequence = calculateCommonQuestSequence(targetDate);

        CommonQuest findCommonQuest =
                retrieveCommonQuestPort.getCommonQuestBySequence(sequence);

        boolean isAnswered = isUserAlreadyAnswered(findUser, command.getTargetDate());

        long totalCount = retrieveUserCommonQuestPort.countByCreatedDateBetween(
                targetDate,
                findUser.getId()
        );

        int limitPlusOne = command.getLimit() + 1;

        List<UserCommonQuestInfoV2Projection> userCommonQuestInfoProjectionListPlusLimit =
                retrieveUserCommonQuestPort.getUserCommonQuestsByCreatedDateV2(
                        targetDate,
                        command.getCursor(),
                        limitPlusOne,
                        findUser.getId()
                );

        boolean hasNext = hasNextData(
                userCommonQuestInfoProjectionListPlusLimit.size(),
                limitPlusOne
        );

        List<UserCommonQuestInfoV2Projection> slicedQuestByDate =
                sliceUnderLimit(
                        hasNext,
                        userCommonQuestInfoProjectionListPlusLimit,
                        command.getLimit()
                );

        Long nextCursor = getNextCursor(
                slicedQuestByDate,
                UserCommonQuestInfoV2Projection::getAnswerId
        );

        List<UserCommonQuestDetailResponseV2Dto> userCommonQuestDetailList =
                slicedQuestByDate.stream()
                        .map(UserCommonQuestDetailResponseV2Dto::of)
                        .toList();

        return UserCommonQuestListResponseV2Dto.from(
                findCommonQuest.getQuestion(),
                isAnswered,
                totalCount,
                userCommonQuestDetailList,
                hasNext,
                nextCursor,
                findCommonQuest.getId()
        );
    }

    private boolean toggleLike(LikeCreateCommand command, boolean alreadyLiked) {
        if (alreadyLiked) {
            deleteLike(command);
            return false;
        }

        createLike(command);
        return true;
    }

    private void deleteLike(LikeCreateCommand command) {
        deleteLikePort.deleteByUserIdAndUserCommonQuestId(
                command.getUserId(),
                command.getUserCommonQuestId()
        );
    }

    private void createLike(LikeCreateCommand command) {
        Like like = LikeMapper.commandToDomain(command);
        createLikePort.createLike(like);
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


    private void validateUserCanWriteCommonQuest(CommonQuest commonQuest) {
        int todaySequence = calculateTodayCommonQuestSequence();

        if (!commonQuest.getSequence().equals(todaySequence)) {
            throw new CustomException(UserCommonQuestErrorCode.COMMON_QUEST_NOT_TODAY);
        }
    }

    private int calculateTodayCommonQuestSequence() {
        long days = ChronoUnit.DAYS.between(COMMON_QUEST_START_DATE, LocalDate.now());

        if (days < 0) {
            throw new CustomException(UserCommonQuestErrorCode.COMMON_QUEST_NOT_TODAY);
        }

        return (int) (days % COMMON_QUEST_CYCLE_SIZE) + 1;
    }

    private int calculateCommonQuestSequence(LocalDate date) {
        long days = ChronoUnit.DAYS.between(COMMON_QUEST_START_DATE, date);

        if (days < 0) {
            throw new CustomException(UserCommonQuestErrorCode.COMMON_QUEST_NOT_TODAY);
        }

        return (int) (days % COMMON_QUEST_CYCLE_SIZE) + 1;
    }

    private void validateNotFuture(LocalDate targetDay){
        if (targetDay.isAfter(LocalDate.now())) {
            throw new CustomException(UserCommonQuestErrorCode.USER_COMMON_QUEST_NOT_FUTURE);
        }
    }
}
