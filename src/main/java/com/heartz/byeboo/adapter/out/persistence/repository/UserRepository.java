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
    SELECT u.id AS id,
           u.currentNumber AS currentNumber,
           u.alarmEnabled AS alarmEnabled
    FROM UserEntity u
    JOIN UserQuestEntity q
      ON u.id = q.userId
     AND q.questId =
        CASE
            WHEN u.journey = com.heartz.byeboo.domain.type.EJourney.FACE_EMOTION
                THEN u.currentNumber - 1
            WHEN u.journey = com.heartz.byeboo.domain.type.EJourney.PROCESS_EMOTION
                THEN u.currentNumber + 29
            WHEN u.journey = com.heartz.byeboo.domain.type.EJourney.PREPARE_REUNION
                THEN u.currentNumber + 59
        END
    WHERE u.currentNumber BETWEEN 2 AND 30
      AND q.createdDate BETWEEN :thresholdStart AND :thresholdEnd
""")
    List<UserIdCurrentNumberProjection> findUsersWithExpiredQuest(
            @Param("thresholdStart") LocalDateTime thresholdStart,
            @Param("thresholdEnd") LocalDateTime thresholdEnd
    );
}
