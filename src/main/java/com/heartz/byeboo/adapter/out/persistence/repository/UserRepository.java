package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserIdCurrentNumberProjection;
import com.heartz.byeboo.domain.type.EPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPlatformAndSerialId(EPlatform platform, String serialId);

    @Query("""
    SELECT u
    FROM UserEntity u
    JOIN UserQuestEntity q
      ON u.id = q.userId
     AND q.questId = (u.currentNumber - 1)
    WHERE u.alarmEnabled = true
      AND q.createdDate BETWEEN :thresholdStart AND :thresholdEnd
""")
    List<UserIdCurrentNumberProjection> findUsersWithExpiredQuest(
            @Param("thresholdStart") LocalDateTime thresholdStart,
            @Param("thresholdEnd") LocalDateTime thresholdEnd
    );

    @Query("SELECT u FROM UserEntity u WHERE u.id in :ids")
    List<UserEntity> findAllByIdIn(List<Long> ids);
}
