package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserCommonQuestRepository extends JpaRepository<UserCommonQuestEntity, Long> {
    boolean existsByUserIdAndCreatedDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
    long deleteByUserIdAndId(Long userId, Long id);
    Optional<UserCommonQuestEntity> findByUserIdAndId(Long userId, Long id);

    @Query("select count(u) from UserCommonQuestEntity u " +
            "where u.createdDate >= :start and u.createdDate <= :end ")
    long countByCreatedDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("select uq from UserCommonQuestEntity uq " +
            "where uq.createdDate >= :start and uq.createdDate <= :end " +
            "and (:cursor is null or uq.id < :cursor) " +
            "order by uq.id desc")
    List<UserCommonQuestEntity> findByDateAndCursor(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end,
                                                  @Param("cursor") Long cursor, Limit limit);

    List<UserCommonQuestEntity> findAllByUserId(Long userId);
}
