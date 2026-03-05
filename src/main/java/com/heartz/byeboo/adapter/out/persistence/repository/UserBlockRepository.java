package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserBlockEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserBlockInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBlockRepository extends JpaRepository<UserBlockEntity, Long> {

    @Query("select ub.id as userBlockId, u.name as userName " +
            "from UserBlockEntity ub " +
            "join UserEntity u on ub.blockedUserId = u.id " +
            "where ub.blockerUserId = :userId")
    List<UserBlockInfoProjection> getUserBlockProjection(@Param("userId") Long userId);

    void deleteAllByBlockedUserIdOrBlockerUserId(Long blockedUserId, Long blockerUserId);
}

