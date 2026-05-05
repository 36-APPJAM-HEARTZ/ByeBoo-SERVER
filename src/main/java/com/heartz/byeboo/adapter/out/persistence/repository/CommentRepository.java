package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.CommentEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommentProjection;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserCommonQuestCommentListProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
            "order by c.createdDate asc")
    List<UserCommentProjection> findRepliesWithWriterByParentId(Long parentId);

    @Query("SELECT c.id AS commentId, c.content AS content, u.name AS writer, u.profileIcon as profileIcon, c.createdDate AS writtenAt, u.id AS writerId " +
            "FROM CommentEntity c JOIN UserEntity u ON c.userId = u.id " +
            "WHERE c.id = :commentId ")
    UserCommentProjection findCommentWithWriterByCommentId(Long commentId);

    @Query("SELECT c.id AS commentId, c.content AS content, u.name AS writer, u.profileIcon as profileIcon, c.createdDate AS writtenAt, u.id AS writerId, " +
            "(SELECT COUNT(r.id)" +
            "            FROM CommentEntity r" +
            "            WHERE r.parentCommentId = c.id" +
            "        ) AS replyCount " +
            "FROM CommentEntity c JOIN UserEntity u ON c.userId = u.id " +
            "WHERE c.userCommonQuestId = :userCommonQuestId and c.parentCommentId IS NULL " +
            "ORDER BY c.createdDate ASC")
    List<UserCommonQuestCommentListProjection> findCommentWithWriterByUserCommonQuestId(Long userCommonQuestId);
}