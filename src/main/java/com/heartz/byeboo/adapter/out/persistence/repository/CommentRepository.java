package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.CommentEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommentProjection;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommonQuestCommentListProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    Optional<CommentEntity> findByUserIdAndId(Long userId, Long id);

    void deleteByUserIdAndId(Long userId, Long id);

    void deleteAllByUserId(Long userId);

    void deleteAllByUserCommonQuestId(Long userCommonQuestId);

    Optional<CommentEntity> findById(Long id);

    @Query("SELECT c.id AS commentId, c.content AS content, u.name AS writer, u.profileIcon as profileIcon, c.createdDate AS writtenAt, u.id AS writerId " +
            "FROM CommentEntity c JOIN UserEntity u ON c.userId = u.id " +
            "WHERE c.parentCommentId = :parentId " +
            "and not exists (select 1 from UserBlockEntity b " +
            "                where (b.blockerUserId = :currentUserId and b.blockedUserId = c.userId) " +
            "                   or (b.blockerUserId = c.userId and b.blockedUserId = :currentUserId)) " +
            "order by c.createdDate asc")
    List<UserCommentProjection> findRepliesWithWriterByParentId(
            @Param("parentId") Long parentId,
            @Param("currentUserId") Long currentUserId
    );

    @Query("SELECT c.id AS commentId, c.content AS content, u.name AS writer, u.profileIcon as profileIcon, c.createdDate AS writtenAt, u.id AS writerId " +
            "FROM CommentEntity c JOIN UserEntity u ON c.userId = u.id " +
            "WHERE c.id = :commentId " +
            "and not exists (select 1 from UserBlockEntity b " +
            "                where (b.blockerUserId = :currentUserId and b.blockedUserId = c.userId) " +
            "                   or (b.blockerUserId = c.userId and b.blockedUserId = :currentUserId)) ")
    UserCommentProjection findCommentWithWriterByCommentId(
            @Param("commentId") Long commentId,
            @Param("currentUserId") Long currentUserId
    );

    @Query("SELECT c.id AS commentId, c.content AS content, u.name AS writer, u.profileIcon as profileIcon, c.createdDate AS writtenAt, u.id AS writerId, " +
            "(SELECT COUNT(r.id) " +
            " FROM CommentEntity r " +
            " WHERE r.parentCommentId = c.id " +
            " and not exists (select 1 from UserBlockEntity b2 " +
            "                 where (b2.blockerUserId = :currentUserId and b2.blockedUserId = r.userId) " +
            "                    or (b2.blockerUserId = r.userId and b2.blockedUserId = :currentUserId)) " +
            ") AS replyCount " +
            "FROM CommentEntity c JOIN UserEntity u ON c.userId = u.id " +
            "WHERE c.userCommonQuestId = :userCommonQuestId " +
            "and c.parentCommentId IS NULL " +
            "and not exists (select 1 from UserBlockEntity b " +
            "                where (b.blockerUserId = :currentUserId and b.blockedUserId = c.userId) " +
            "                   or (b.blockerUserId = c.userId and b.blockedUserId = :currentUserId)) " +
            "ORDER BY c.createdDate ASC")
    List<UserCommonQuestCommentListProjection> findCommentWithWriterByUserCommonQuestId(
            @Param("userCommonQuestId") Long userCommonQuestId,
            @Param("currentUserId") Long currentUserId
    );

    @Modifying
    @Query("delete from CommentEntity c where c.parentCommentId = :parentCommentId")
    void deleteAllReplyByUserId(@Param("parentCommentId") Long parentCommentId);
}