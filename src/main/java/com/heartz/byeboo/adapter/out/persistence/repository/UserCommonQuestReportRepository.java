package com.heartz.byeboo.adapter.out.persistence.repository;

import com.heartz.byeboo.adapter.out.persistence.entity.UserCommonQuestReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCommonQuestReportRepository extends JpaRepository<UserCommonQuestReportEntity, Long> {

    @Modifying
    @Query("DELETE FROM UserCommonQuestReportEntity r WHERE r.userCommonQuestId IN " +
            "(SELECT uq.id FROM UserCommonQuestEntity uq WHERE uq.userId = :userId)")
    void deleteAllByReportedUserId(@Param("userId") Long userId);
}
